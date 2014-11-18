package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.ninetwozero.bf4intel.database.dao.ProfileDAO;
import com.ninetwozero.bf4intel.database.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.database.dao.soldieroverview.SoldierOverviewDAO;

public class InitialMigration extends BaseMigration {
    @Override
    protected void doMigration(final SQLiteDatabase db) {
        createSummarizedSoldierStatsTable(db);
        createProfileTable(db);
        createSoldierOverviewTable(db);
    }

    private void createSummarizedSoldierStatsTable(final SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + SummarizedSoldierStatsDAO.TABLE_NAME + "(" +
                "rowId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "soldierId TEXT," +
                "soldierName TEXT," +
                "tag TEXT," +
                "picture TEXT," +
                "userId TEXT," +
                "rank INTEGER," +
                "platform INTEGER," +
                "game INTEGER" +
            ")"
        );
    }

    private void createProfileTable(final SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + ProfileDAO.TABLE_NAME + "(" +
                "userId TEXT PRIMARY KEY," +
                "username TEXT," +
                "gravatarMd5 TEXT" +
            ")"
        );
    }

    private void createSoldierOverviewTable(final SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + SoldierOverviewDAO.TABLE_NAME + "(" +
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
