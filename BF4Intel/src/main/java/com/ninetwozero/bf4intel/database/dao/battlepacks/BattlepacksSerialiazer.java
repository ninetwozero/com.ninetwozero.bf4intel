package com.ninetwozero.bf4intel.database.dao.battlepacks;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.battlepacks.Battlepacks;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class BattlepacksSerialiazer implements TypeSerializer<Battlepacks> {
    @Override
    public Battlepacks unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, Battlepacks.class);
    }

    @Override
    public void pack(Battlepacks battlepacks, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(battlepacks, Battlepacks.class));
    }

    @Override
    public String toSql(Battlepacks battlepacks) {
        return String.valueOf(battlepacks);
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
