package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.database.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponStatsDAO;

public class MigrationToOneZeroOne extends BaseMigration {
    @Override
    protected void doMigration(SQLiteDatabase db) {
        dropTable(db, AssignmentsDAO.TABLE_NAME);
        dropTable(db, AwardsDAO.TABLE_NAME);
        dropTable(db, WeaponStatsDAO.TABLE_NAME);

        createRegularJsonTable(db, AssignmentsDAO.TABLE_NAME);
        createRegularJsonTable(db, AwardsDAO.TABLE_NAME);
        createRegularJsonTable(db, WeaponStatsDAO.TABLE_NAME);
    }
}
