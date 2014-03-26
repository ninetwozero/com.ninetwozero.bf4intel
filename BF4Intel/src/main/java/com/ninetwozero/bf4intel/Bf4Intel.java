package com.ninetwozero.bf4intel;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ninetwozero.bf4intel.database.dao.ProfileDAO;
import com.ninetwozero.bf4intel.database.dao.SummarizedSoldierStatsDAO;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;


public class Bf4Intel extends Application {
    private static final String DB_NAME = "bf4intel.db";
    private static Bf4Intel instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Sprinkles sprinkles = Sprinkles.init(getApplicationContext(), DB_NAME, 0);
        sprinkles.addMigration(getInitialMigration());
    }

    public static boolean isConnectedToNetwork() {
        final ConnectivityManager manager = (ConnectivityManager) instance.getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo network = manager.getActiveNetworkInfo();
        return network != null && network.isConnected();
    }

    private Migration getInitialMigration() {
        Migration migration = new Migration();
        migration.createTable(SummarizedSoldierStatsDAO.class);
        migration.createTable(ProfileDAO.class);
        return migration;
    }
}
