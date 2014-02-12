package com.ninetwozero.bf4intel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.login.Persona;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CupboardSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bf4intel.db";
    private static final int DATABASE_VERSION = 1;

    static {
        cupboard().register(Profile.class);
        cupboard().register(SummarizedSoldierStats.class);
        cupboard().register(Persona.class);
    }

    public CupboardSQLiteOpenHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
