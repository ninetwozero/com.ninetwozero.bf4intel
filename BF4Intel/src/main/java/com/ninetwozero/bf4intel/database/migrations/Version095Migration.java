package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;


import com.ninetwozero.bf4intel.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsDAO;
import com.ninetwozero.bf4intel.dao.stats.vehicles.VehicleStatsDAO;
import com.ninetwozero.bf4intel.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.vehicles.VehicleUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockDAO;

import se.emilsjolander.sprinkles.Migration;

public class Version095Migration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase database) {
        createRegularJsonTable(database, WeaponStatsDAO.TABLE_NAME);
        createRegularJsonTable(database, VehicleStatsDAO.TABLE_NAME);
        //createRegularJsonTable(database, BattleReportDAO.TABLE_NAME);
        createRegularJsonTable(database, DetailedStatsDAO.TABLE_NAME);

        createRegularJsonTable(database, WeaponUnlockDAO.TABLE_NAME);
        createRegularJsonTable(database, VehicleUnlockDAO.TABLE_NAME);
        createRegularJsonTable(database, KitUnlockDAO.TABLE_NAME);

        createRegularJsonTable(database, AssignmentsDAO.TABLE_NAME);
        createRegularJsonTable(database, AwardsDAO.TABLE_NAME);
    }

    private void createRegularJsonTable(SQLiteDatabase database, String tableName) {
        database.execSQL(
            "CREATE TABLE " + tableName + "(" +
                "soldierId TEXT," +
                "soldierName TEXT," +
                "platformId INTEGER," +
                "json TEXT," +
                "version INTEGER," +
                "PRIMARY KEY(soldierId, platformId)" +
                ")"
        );
    }
}
