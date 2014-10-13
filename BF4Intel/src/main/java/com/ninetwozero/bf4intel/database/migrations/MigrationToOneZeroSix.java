package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.ninetwozero.bf4intel.database.dao.login.SummarizedSoldierStatsDAO;

public class MigrationToOneZeroSix extends BaseMigration {

    @Override
    protected void doMigration(SQLiteDatabase db) {
        dropTable(db, SummarizedSoldierStatsDAO.TABLE_NAME);
        createNewSummarizedSoldierStatsTable(db);
        // (SummarizedSoldierStatsDAO);

    }

    private void createNewSummarizedSoldierStatsTable(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + SummarizedSoldierStatsDAO.TABLE_NAME + "(" +
                "soldierId TEXT," +
                "soldierName TEXT," +
                "tag TEXT," +
                "picture TEXT," +
                "userId TEXT," +
                "rank INTEGER," +
                "platformId INTEGER," +
                "game INTEGER," +
                "lastAccess INTEGER," +
                "PRIMARY KEY(soldierId, platformId)" +
            ")"
        );
    }
}
