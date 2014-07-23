package com.ninetwozero.bf4intel.database.dao.unlocks.vehicles;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class SortedVehicleUnlocksSerializer implements TypeSerializer<SortedVehicleUnlocks> {
    @Override
    public SortedVehicleUnlocks unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, SortedVehicleUnlocks.class);
    }

    @Override
    public void pack(SortedVehicleUnlocks sortedVehicleUnlocks, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(sortedVehicleUnlocks, SortedVehicleUnlocks.class));
    }

    @Override
    public String toSql(SortedVehicleUnlocks sortedVehicleUnlocks) {
        return String.valueOf(sortedVehicleUnlocks.getSortedVehicles().size());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}