package com.ninetwozero.bf4intel.database.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.ninetwozero.bf4intel.database.dao.battlepacks.BattlepacksDAO;

public class MigrationToOneOneTree extends BaseMigration {

    @Override
    protected void doMigration(SQLiteDatabase db) {
        createRegularJsonTable(db, BattlepacksDAO.TABLE_NAME);
    }
}
