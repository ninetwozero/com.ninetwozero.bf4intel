package com.ninetwozero.bf4intel.dao.awards;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class SortedAwardContainerSerializer implements TypeSerializer<SortedAwardContainer> {
    @Override
    public SortedAwardContainer unpack(Cursor cursor, String columnName) {
        return GsonProvider.getInstance().fromJson(
            cursor.getString(cursor.getColumnIndex(columnName)),
            SortedAwardContainer.class
        );
    }

    @Override
    public void pack(SortedAwardContainer sortedAwardContainer, ContentValues contentValues, String columnName) {
        contentValues.put(
            columnName,
            GsonProvider.getInstance().toJson(
                sortedAwardContainer,
                SortedAwardContainer.class
            )
        );
    }

    @Override
    public String toSql(SortedAwardContainer sortedAwardContainer) {
        return String.valueOf(sortedAwardContainer);
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
