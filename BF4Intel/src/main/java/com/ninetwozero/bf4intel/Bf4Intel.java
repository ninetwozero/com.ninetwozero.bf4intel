package com.ninetwozero.bf4intel;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ninetwozero.bf4intel.dao.ProfileDAO;
import com.ninetwozero.bf4intel.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.dao.assignments.SortedAssignmentContainerSerializer;
import com.ninetwozero.bf4intel.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.dao.awards.SortedAwardContainerSerializer;
import com.ninetwozero.bf4intel.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.dao.soldieroverview.SoldierOverviewDAO;
import com.ninetwozero.bf4intel.dao.soldieroverview.SoldierOverviewSerializer;
import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsContainerSerializer;
import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsDAO;
import com.ninetwozero.bf4intel.dao.stats.vehicles.GroupedVehicleStatisticsSerializer;
import com.ninetwozero.bf4intel.dao.stats.vehicles.VehicleStatsDAO;
import com.ninetwozero.bf4intel.dao.stats.weapons.WeaponStatisticsSerializer;
import com.ninetwozero.bf4intel.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.SortedWeaponUnlocksSerializer;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsContainer;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStatsContainer;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

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

        Sprinkles sprinkles = Sprinkles.init(getApplicationContext(), DB_NAME, 0);
        setupSerializers(sprinkles);
        setupMigrations(sprinkles);
    }

    private void setupSerializers(Sprinkles sprinkles) {
        sprinkles.registerType(SoldierOverview.class, new SoldierOverviewSerializer());

        sprinkles.registerType(WeaponStatistics.class, new WeaponStatisticsSerializer());
        sprinkles.registerType(GroupedVehicleStatsContainer.class, new GroupedVehicleStatisticsSerializer());
        //sprinkles.registerType(BattleReportStatistics.class, new BattleReportStatisticsSerializer()); ??
        sprinkles.registerType(DetailedStatsContainer.class, new DetailedStatsContainerSerializer());

        sprinkles.registerType(SortedWeaponUnlocks.class, new SortedWeaponUnlocksSerializer());

        sprinkles.registerType(SortedAssignmentContainer.class, new SortedAssignmentContainerSerializer());
        sprinkles.registerType(SortedAwardContainer.class, new SortedAwardContainerSerializer());
    }

    private void setupMigrations(Sprinkles sprinkles) {
        sprinkles.addMigration(getInitialMigration());
        sprinkles.addMigration(getMigrationToVersion_0_9_6());
    }

    private Migration getMigrationToVersion_0_9_6() {
        Migration migration = new Migration();
        migration.createTable(WeaponStatsDAO.class);
        migration.createTable(VehicleStatsDAO.class);
        //migration.createTable(BattleReportDAO.class);
        migration.createTable(DetailedStatsDAO.class);

        migration.createTable(WeaponUnlockDAO.class);

        migration.createTable(AssignmentsDAO.class);
        migration.createTable(AwardsDAO.class);
        return migration;
    }

    private Migration getInitialMigration() {
        Migration migration = new Migration();
        migration.createTable(SummarizedSoldierStatsDAO.class);
        migration.createTable(ProfileDAO.class);
        migration.createTable(SoldierOverviewDAO.class);
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
