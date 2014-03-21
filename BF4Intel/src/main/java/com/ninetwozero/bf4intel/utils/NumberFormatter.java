package com.ninetwozero.bf4intel.utils;

import java.util.Locale;

public class NumberFormatter {
    public static String format(final long number) {
        return String.format(Locale.getDefault(), "%,d", number);
    }
    public static String format(final double number) {
        return String.format(Locale.getDefault(), "%.2f", number);
    }
}
