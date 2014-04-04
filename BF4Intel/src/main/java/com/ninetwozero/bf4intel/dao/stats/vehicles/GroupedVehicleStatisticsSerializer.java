package com.ninetwozero.bf4intel.dao.stats.vehicles;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStatsContainer;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class GroupedVehicleStatisticsSerializer implements TypeSerializer<GroupedVehicleStatsContainer> {
    @Override
    public GroupedVehicleStatsContainer unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, GroupedVehicleStatsContainer.class);
    }

    @Override
    public void pack(GroupedVehicleStatsContainer vehicleStatistics, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(vehicleStatistics, GroupedVehicleStatsContainer.class));
    }

    @Override
    public String toSql(GroupedVehicleStatsContainer vehicleStatistics) {
        return String.valueOf(vehicleStatistics.getItems().size());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
