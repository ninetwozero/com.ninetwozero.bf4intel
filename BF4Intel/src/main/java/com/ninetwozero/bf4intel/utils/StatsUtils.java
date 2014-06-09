package com.ninetwozero.bf4intel.utils;

public class StatsUtils {
    public static double calculateKillsPerMinute(final int killCount, final long time) {
        if (time > DateTimeUtils.MINUTE) {
            return (((double) killCount) / time) * DateTimeUtils.MINUTE;
        } else {
            return killCount;
        }
    }
}
