package com.ninetwozero.bf4intel.utils;

import com.ninetwozero.bf4intel.R;

public class PersonaUtils {
    private static final int GAME_ID_BF4 = 2048;
    private static final int GAME_ID_MOHW = 4096;

    public static String getPlatformNameFromPlatformId(final int id) {
        switch (id) {
            case 0:
            case 1:
                return "PC";
            case 2:
                return "XBOX 360";
            case 4:
                return "PS3";
            case 8:
                return "XBOX";
            case 16:
                return "PS2";
            case 32:
                return "PS4";
            case 64:
                return "XBOX ONE";
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

    /*
        Due to Battlelog doing the search by the soldier name (and not the username), we need to
        get the users that are playing BF4 but not MOHW (gameId >= 2048 && gameId < 4096)

        OR

        gameId >= BF4+MOHW (2048+4096) as PC players "stack up"

        Info: http://battlelog.battlefield.com/bf4/user/haruhi00/
        Info: http://battlelog.battlefield.com/bf4/user/cursef (MOHW)
        Info: http://battlelog.battlefield.com/bf4/user/Hyphon (BF4+MOHW on PC)
    */

    public static boolean isBf4Soldier(int gameId) {
        return (gameId >= GAME_ID_BF4 && gameId < GAME_ID_MOHW) || (gameId >= GAME_ID_MOHW+GAME_ID_BF4);
    }
}
