package com.ninetwozero.bf4intel.utils;

import android.content.Context;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class LeaderboardUtils {
    private static final String KEY_TOTAL = "total";
    private static final String KEY_PERSONAL = "personal_record";
    private static final String KEY_PREFIX_KILLS = "c_";
    private static final String KEY_PREFIX_SCORE = "sc_";

    private static final Map<String, Integer> strings = new HashMap<String, Integer>() {
        {
            put("kdr", R.string.lb_sctype_kdr);
            put("kills", R.string.lb_sctype_kills);
            put("skill", R.string.lb_sctype_skill);
            put("spm", R.string.lb_sctype_spm);
            put("score", R.string.lb_sctype_score);
            put("accuracy_all", R.string.lb_sctype_accuracy);
            put("win_loss_ratio", R.string.lb_sctype_wlr);

            put("c_kany__sa_g", R.string.lb_sctype_time);
            put("c_vA__de_g", R.string.lb_sctype_destroyed);
            put("c___hsh_g", R.string.lb_sctype_headshots);
            put("c___r_g", R.string.lb_sctype_repairs);
            put("c___re_g", R.string.lb_sctype_revives);

            // Not sure if used - they were in the data though
            put("WARSAW_ID_BL_GAMEPUSH_GEOLB_SCORETYPE_HEALS", R.string.lb_sctype_heals);
            put("WARSAW_ID_BL_GAMEPUSH_GEOLB_SCORETYPE_HIT_PERCENTAGE", R.string.lb_sctype_hit_percentage);
            put("WARSAW_ID_BL_GAMEPUSH_GEOLB_SCORETYPE_KILL_STREAK", R.string.lb_sctype_killstreak);
            put("WARSAW_ID_BL_GAMEPUSH_GEOLB_SCORETYPE_LONGEST_HEADSHOT", R.string.lb_sctype_longest_hs);

        }
    };

    public static String getScoreString(final Context context, final String fullKey, final double score) {
        final String key = fullKey.split("\\.")[0];
        int resource = R.string.na;

        if (strings.containsKey(key)) {
            resource = strings.get(key);
        } else if (key.startsWith(KEY_PREFIX_KILLS)) {
            resource = R.string.lb_sctype_kills;
        } else if (key.startsWith(KEY_PREFIX_SCORE)) {
            resource = R.string.lb_sctype_score;
        }

        if (resource == R.string.na) {
            return context.getString(resource);
        }

        // Check if the double is actually an int
        if (score % 1 == 0) {
            return String.format(context.getString(resource), NumberFormatter.format((int) score));
        } else {
            return String.format(context.getString(resource), NumberFormatter.format(score));
        }
    }
}
