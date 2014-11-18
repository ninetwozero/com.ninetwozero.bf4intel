package com.ninetwozero.bf4intel;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ninetwozero.bf4intel.database.dao.assignments.SortedAssignmentContainerSerializer;
import com.ninetwozero.bf4intel.database.dao.awards.SortedAwardContainerSerializer;
import com.ninetwozero.bf4intel.database.dao.soldieroverview.SoldierOverviewSerializer;
import com.ninetwozero.bf4intel.database.dao.stats.details.DetailedStatsContainerSerializer;
import com.ninetwozero.bf4intel.database.dao.stats.vehicles.GroupedVehicleStatisticsSerializer;
import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponStatisticsSerializer;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.SortedKitUnlocksSerializer;
import com.ninetwozero.bf4intel.database.dao.unlocks.vehicles.SortedVehicleUnlocksSerializer;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.SortedWeaponUnlocksSerializer;
import com.ninetwozero.bf4intel.database.migrations.InitialMigration;
import com.ninetwozero.bf4intel.database.migrations.MigrationToOneZeroOne;
import com.ninetwozero.bf4intel.database.migrations.MigrationToOneZeroSix;
import com.ninetwozero.bf4intel.database.migrations.MigrationToZeroNineSix;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsContainer;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStatsContainer;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

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
        sprinkles.registerType(SortedVehicleUnlocks.class, new SortedVehicleUnlocksSerializer());
        sprinkles.registerType(SortedKitUnlocks.class, new SortedKitUnlocksSerializer());

        sprinkles.registerType(SortedAssignmentContainer.class, new SortedAssignmentContainerSerializer());
        sprinkles.registerType(SortedAwardContainer.class, new SortedAwardContainerSerializer());
    }

    private void setupMigrations(Sprinkles sprinkles) {
        sprinkles.addMigration(new InitialMigration());
        sprinkles.addMigration(new MigrationToZeroNineSix());
        sprinkles.addMigration(new MigrationToOneZeroOne());
        sprinkles.addMigration(new MigrationToOneZeroSix());
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
