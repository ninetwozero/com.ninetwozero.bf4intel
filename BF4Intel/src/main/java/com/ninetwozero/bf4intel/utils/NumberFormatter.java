package com.ninetwozero.bf4intel.utils;

import java.math.BigDecimal;
import java.util.Locale;

public class NumberFormatter {
    public static String format(final long number) {
        return String.format(Locale.getDefault(), "%,d", number);
    }

    public static String format(final double number) {
        return String.format(Locale.getDefault(), "%.2f", number);
    }

    public static String format(final BigDecimal number) {
        return String.format(Locale.getDefault(), "%.2f", number.doubleValue());
    }

    public static String percentageFormat(final double number) {
        return String.format(Locale.getDefault(), "%.2f%s", number * 100, "%");
    }
}
