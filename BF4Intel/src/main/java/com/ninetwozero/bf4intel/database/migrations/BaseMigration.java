package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;

@Deprecated
public class BaseMigration extends Migration {
    @Override
    protected void doMigration(final SQLiteDatabase sqLiteDatabase) {
    }

    protected void dropTable(final SQLiteDatabase db, final String tableName) {
        db.execSQL("DROP TABLE " + tableName);
    }

    protected void createRegularJsonTable(final SQLiteDatabase db, final String tableName) {
        db.execSQL(
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
