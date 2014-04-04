package com.ninetwozero.bf4intel.dao.stats.weapons;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class WeaponStatisticsSerializer implements TypeSerializer<WeaponStatistics> {
    @Override
    public WeaponStatistics unpack(Cursor cursor, String columnName) {
        final String json = cursor.getString(cursor.getColumnIndex(columnName));
        return GsonProvider.getInstance().fromJson(json, WeaponStatistics.class);
    }

    @Override
    public void pack(WeaponStatistics weaponStatistics, ContentValues contentValues, String columnName) {
        contentValues.put(columnName, GsonProvider.getInstance().toJson(weaponStatistics, WeaponStatistics.class));
    }

    @Override
    public String toSql(WeaponStatistics weaponStatistics) {
        return String.valueOf(weaponStatistics.getWeaponsList().size());
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
