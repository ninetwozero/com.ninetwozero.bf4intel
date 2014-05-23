package com.ninetwozero.bf4intel.database.dao.soldieroverview;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class SoldierOverviewSerializer implements TypeSerializer<SoldierOverview> {
    @Override
    public SoldierOverview unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, SoldierOverview.class);
    }

    @Override
    public void pack(SoldierOverview soldierOverview, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(soldierOverview, SoldierOverview.class));
    }

    @Override
    public String toSql(SoldierOverview soldierOverview) {
        return String.valueOf(soldierOverview.getCurrentRank().getLevel());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
