package com.ninetwozero.bf4intel;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ninetwozero.bf4intel.dao.ProfileDAO;
import com.ninetwozero.bf4intel.dao.login.SummarizedSoldierStatsDAO;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;


public class Bf4Intel extends Application {
    private static final String DB_NAME = "bf4intel.db";
    private static Bf4Intel instance;
    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        requestQueue = Volley.newRequestQueue(this);
        setupMigrations();
    }

    private void setupMigrations() {
        Sprinkles sprinkles = Sprinkles.init(getApplicationContext(), DB_NAME, 0);
        sprinkles.addMigration(getInitialMigration());
    }

    private Migration getInitialMigration() {
        Migration migration = new Migration();
        migration.createTable(SummarizedSoldierStatsDAO.class);
        migration.createTable(ProfileDAO.class);
        return migration;
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static boolean isConnectedToNetwork() {
        final ConnectivityManager manager = (ConnectivityManager) instance.getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo network = manager.getActiveNetworkInfo();
        return network != null && network.isConnected();
    }
}
