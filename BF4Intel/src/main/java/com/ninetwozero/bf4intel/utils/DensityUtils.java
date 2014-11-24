package com.ninetwozero.bf4intel.utils;

import android.content.res.Resources;

public class DensityUtils {
    public final static float SCALE = Resources.getSystem().getDisplayMetrics().density;

    public static int toPixels(final float dp) {
        return (int) (dp * SCALE + 0.5f);
    }

    public static float toDips(final int pixels) {
        return (pixels / SCALE + 0.5f);
    }
}