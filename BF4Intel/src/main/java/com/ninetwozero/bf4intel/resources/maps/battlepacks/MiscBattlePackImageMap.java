package com.ninetwozero.bf4intel.resources.maps.battlepacks;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class MiscBattlePackImageMap {
    /*
    TODO: Need to download images for emblems, camoflague and a few others
     */

    private static final Map<String, Integer> images = new HashMap<String, Integer>() {
        {
            put("BRONZE", R.drawable.battlepack_bronze);
            put("SILVER", R.drawable.battlepack_silver);
            put("GOLD", R.drawable.battlepack_gold);
            put("PREMIUM", R.drawable.battlepack_premium);
            put("WEAPON", R.drawable.battlepack_kit);
            put("WARSAW_ID_P_ULTIMATE_25", R.drawable.battlepack_boost_25);
            put("WARSAW_ID_P_ULTIMATE_50", R.drawable.battlepack_boost_50);
            put("WARSAW_ID_P_ULTIMATE_100", R.drawable.battlepack_boost_100);
            put("WARSAW_ID_P_ULTIMATE_200", R.drawable.battlepack_boost_200);
        }
    };

    public static int get(final String key) {
        return images.containsKey(key)? images.get(key) : R.drawable.acc_none;
    }
}
