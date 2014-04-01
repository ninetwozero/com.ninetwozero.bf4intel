package com.ninetwozero.bf4intel.utils;

import android.content.ContentValues;
import android.database.Cursor;

import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;

import se.emilsjolander.sprinkles.typeserializers.SqlType;
import se.emilsjolander.sprinkles.typeserializers.TypeSerializer;

public class SortedAssignmentContainerSerializer implements TypeSerializer<SortedAssignmentContainer> {
    @Override
    public SortedAssignmentContainer unpack(Cursor cursor, String columnName) {
        return GsonProvider.getInstance().fromJson(
            cursor.getString(cursor.getColumnIndex(columnName)),
            SortedAssignmentContainer.class
        );
    }

    @Override
    public void pack(SortedAssignmentContainer sortedAssignmentContainer, ContentValues contentValues, String columnName) {
        contentValues.put(
            columnName,
            GsonProvider.getInstance().toJson(
                sortedAssignmentContainer,
                SortedAssignmentContainer.class
            )
        );
    }

    @Override
    public String toSql(SortedAssignmentContainer sortedAssignmentContainer) {
        return String.valueOf(sortedAssignmentContainer);
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.TEXT;
    }
}
