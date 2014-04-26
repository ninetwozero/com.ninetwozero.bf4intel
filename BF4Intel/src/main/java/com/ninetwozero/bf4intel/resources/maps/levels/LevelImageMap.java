package com.ninetwozero.bf4intel.resources.maps.levels;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LevelImageMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {

            // MP
            put("MP_TREMORS", R.drawable.mp_tremors);
            put("MP_FLOODED", R.drawable.mp_flooded);
            put("MP_JOURNEY", R.drawable.mp_journey);
            put("MP_RESORT", R.drawable.mp_resort);
            put("MP_DAMAGE", R.drawable.mp_damage);
            put("MP_NAVAL", R.drawable.mp_naval);
            put("MP_THEDISH", R.drawable.mp_thedish);
            put("MP_PRISON", R.drawable.mp_prison);
            put("MP_SIEGE", R.drawable.mp_siege);
            put("MP_ABANDONED", R.drawable.mp_abandoned);

            // XP0
            put("XP0_CASPIAN", R.drawable.mp_xp0_caspian);
            put("XP0_FIRESTORM", R.drawable.mp_xp0_firestorm);
            put("XP0_OMAN", R.drawable.mp_xp0_oman);
            put("XP0_METRO", R.drawable.mp_xp0_metro);

            // XP1
            put("XP1_001", R.drawable.mp_xp1_001);
            put("XP1_002", R.drawable.mp_xp1_002);
            put("XP1_003", R.drawable.mp_xp1_003);
            put("XP1_004", R.drawable.mp_xp1_004);

            // XP2
            put("XP2_001", R.drawable.mp_xp2_001);
            put("XP2_002", R.drawable.mp_xp2_002);
            put("XP2_003", R.drawable.mp_xp2_003);
            put("XP2_004", R.drawable.mp_xp2_004);
        }
    };

    public static Integer get(final String key) {
        if (key == null) {
            return R.drawable.acc_none;
        }
        return map.containsKey(key.toUpperCase(Locale.getDefault())) ? map.get(key.toUpperCase()) : R.drawable.acc_none;
    }
}
