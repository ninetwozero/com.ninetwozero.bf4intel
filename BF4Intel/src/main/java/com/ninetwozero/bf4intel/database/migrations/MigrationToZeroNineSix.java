package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.database.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.database.dao.stats.details.DetailedStatsDAO;
import com.ninetwozero.bf4intel.database.dao.stats.vehicles.VehicleStatsDAO;
import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.vehicles.VehicleUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockDAO;

public class MigrationToZeroNineSix extends BaseMigration {
    @Override
    protected void doMigration(final SQLiteDatabase db) {
        createRegularJsonTable(db, WeaponStatsDAO.TABLE_NAME);
        createRegularJsonTable(db, VehicleStatsDAO.TABLE_NAME);
        //createRegularJsonTable(db, BattleReportDAO.TABLE_NAME);
        createRegularJsonTable(db, DetailedStatsDAO.TABLE_NAME);

        createRegularJsonTable(db, WeaponUnlockDAO.TABLE_NAME);
        createRegularJsonTable(db, VehicleUnlockDAO.TABLE_NAME);
        createRegularJsonTable(db, KitUnlockDAO.TABLE_NAME);

        createRegularJsonTable(db, AssignmentsDAO.TABLE_NAME);
        createRegularJsonTable(db, AwardsDAO.TABLE_NAME);

    }
}
