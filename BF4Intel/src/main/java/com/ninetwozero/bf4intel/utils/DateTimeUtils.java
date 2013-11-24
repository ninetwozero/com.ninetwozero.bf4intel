package com.ninetwozero.bf4intel.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class DateTimeUtils {
    public final static int MINUTE = 60;
    public final static int HOUR = MINUTE*60;
    public final static int DAY = HOUR*24;

    private final static int OUTPUT_LIMIT = 2;
    private final static Map<Integer, String> mLiteralMapping = new LinkedHashMap<Integer, String>() {{
        put(DAY, "D");
        put(HOUR, "H");
        put(MINUTE, "M");
    }};

    public static String toLiteral(final int seconds) {
        final StringBuilder stringBuilder = new StringBuilder();
        int secondsKeeper = seconds;
        int count = 0;
        int numOutputs = 0;

        for (int key : mLiteralMapping.keySet()) {
            count = secondsKeeper / key;
            if (count > 0 || numOutputs > 0) {
                stringBuilder.append(count).append(mLiteralMapping.get(key)).append(" ");
                secondsKeeper = secondsKeeper % key;
                if (++numOutputs == OUTPUT_LIMIT) {
                    break;
                }
            }
        }
        return stringBuilder.toString().trim();
    }

    public static String toRelative(final long timeInSeconds) {
        return "TODO";
        DateTimeUtils.getRelativeDateTimeString()
    }
}
