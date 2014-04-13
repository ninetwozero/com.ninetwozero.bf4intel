package com.ninetwozero.bf4intel.dao.unlocks;

public enum SortMode {
    CATEGORIZED, PROGRESS;

    public static SortMode fromOrdinal(int ordinal) {
        for (SortMode value : values()) {
            if (value.ordinal() == ordinal) {
                return value;
            }
        }
        throw new IllegalArgumentException("No SortMode matches " + ordinal);
    }
}
