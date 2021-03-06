package com.ninetwozero.bf4intel.database.dao.unlocks;

public enum SortMode {
    ALL, CATEGORIZED, PROGRESS;

    public static SortMode fromOrdinal(int ordinal) {
        for (SortMode value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        throw new IllegalArgumentException("No SortMode matches " + ordinal);
    }
}
