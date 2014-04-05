package com.ninetwozero.bf4intel.dao.unlocks.weapons;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class SortedWeaponUnlocksSerializer implements TypeSerializer<SortedWeaponUnlocks> {
    @Override
    public SortedWeaponUnlocks unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, SortedWeaponUnlocks.class);
    }

    @Override
    public void pack(SortedWeaponUnlocks sortedWeaponUnlocks, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(sortedWeaponUnlocks, SortedWeaponUnlocks.class));
    }

    @Override
    public String toSql(SortedWeaponUnlocks sortedWeaponUnlocks) {
        return String.valueOf(sortedWeaponUnlocks.getSortedWeaponUnlocksMap().size());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}