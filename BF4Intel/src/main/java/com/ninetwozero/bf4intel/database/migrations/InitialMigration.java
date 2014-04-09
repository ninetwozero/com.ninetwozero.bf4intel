package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.ninetwozero.bf4intel.dao.ProfileDAO;
import com.ninetwozero.bf4intel.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.dao.soldieroverview.SoldierOverviewDAO;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;

import se.emilsjolander.sprinkles.Migration;

public class InitialMigration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase database) {
        createSummarizedSoldierStatsTable(database);
        createProfileTable(database);
        createSoldierOverviewTable(database);
    }

    private void createSummarizedSoldierStatsTable(SQLiteDatabase database) {
        database.execSQL(
            "CREATE TABLE " + SummarizedSoldierStatsDAO.TABLE_NAME + "(" +
                "rowId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "soldierId TEXT," +
                "soldierName TEXT," +
                "tag TEXT," +
                "picture TEXT," +
                "userId TEXT," +
                "rank INTEGER," +
                "platform INTEGER," +
                "game INTEGER," +
            ")"
        );
    }

    private void createProfileTable(SQLiteDatabase database) {
        database.execSQL(
            "CREATE TABLE " + ProfileDAO.TABLE_NAME + "(" +
                "userId TEXT PRIMARY KEY," +
                "username TEXT," +
                "gravatarMd5 TEXT" +
            ")"
        );
    }

    private void createSoldierOverviewTable(SQLiteDatabase database) {
        database.execSQL(
            "CREATE TABLE " + SoldierOverviewDAO.TABLE_NAME + "(" +
                "soldierId TEXT," +
                "soldierName TEXT," +
                "platformId INTEGER" +
                "json TEXT," +
                "version INTEGER," +
                "PRIMARY KEY(soldierId, platformId)" +
            ")"
        );
    }
}
