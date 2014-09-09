package com.ninetwozero.bf4intel.database.dao.stats.details;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsContainer;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class DetailedStatsContainerSerializer implements TypeSerializer<DetailedStatsContainer> {
    @Override
    public DetailedStatsContainer unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, DetailedStatsContainer.class);
    }

    @Override
    public void pack(DetailedStatsContainer detailedStats, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(detailedStats, DetailedStatsContainer.class));
    }

    @Override
    public String toSql(DetailedStatsContainer detailedStats) {
        return String.valueOf(detailedStats.getGroups().size());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
