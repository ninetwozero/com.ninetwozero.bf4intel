package com.ninetwozero.bf4intel.database.dao.unlocks.kits;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class SortedKitUnlocksSerializer implements TypeSerializer<SortedKitUnlocks> {
    @Override
    public SortedKitUnlocks unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, SortedKitUnlocks.class);
    }

    @Override
    public void pack(SortedKitUnlocks sortedKitUnlocks, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(sortedKitUnlocks, SortedKitUnlocks.class));
    }

    @Override
    public String toSql(SortedKitUnlocks sortedKitUnlocks) {
        return String.valueOf(sortedKitUnlocks.getSortedUnlocks().size());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}