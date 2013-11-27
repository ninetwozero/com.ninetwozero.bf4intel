package com.ninetwozero.bf4intel.base.utils;

import com.ninetwozero.bf4intel.R;

public class PersonaUtils {
    public static String getPlatformNameFromPlatformId(final int id) {
        switch (id) {
            case 0:
            case 1:
                return "PC";
            case 2:
                return "XBOX 360";
            case 4:
                return "PS3";
            default:
                return "N/A";
        }
    }

    public static int getIconForKit(final int id) {
        switch (id) {
            case 1:
                return R.drawable.kit_icon_medic;
            case 2:
                return R.drawable.kit_icon_engineer;
            case 8:
                return R.drawable.kit_icon_recon;
            case 32:
                return R.drawable.kit_icon_support;
            default:
                return R.drawable.kit_icon_commander;
        }
    }
}
