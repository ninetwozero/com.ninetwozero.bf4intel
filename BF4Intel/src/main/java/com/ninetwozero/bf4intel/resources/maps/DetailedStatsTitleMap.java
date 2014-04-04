package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class DetailedStatsTitleMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("sc_assault", R.string.assault_score);
            put("sc_engineer", R.string.engineer_score);
            put("sc_recon", R.string.recon_score);
            put("sc_support", R.string.support_score);
            put("sc_commander", R.string.commander_score);
            put("sc_squad", R.string.squad_score);
            put("sc_vehicle", R.string.vehicle_score);
            put("sc_award", R.string.award_score);
            put("sc_unlock", R.string.unlock_score);
            put("sc_total", R.string.total_score);

            put("kills", R.string.assault_score);
            put("deaths", R.string.engineer_score);
            put("kill_assists", R.string.recon_score);
            put("kd_ratio", R.string.support_score);
            put("wins", R.string.commander_score);
            put("losses", R.string.squad_score);
            put("shots_fired", R.string.vehicle_score);
            put("shots_hits", R.string.award_score);
            put("accuracy", R.string.unlock_score);

            put("conquest", R.string.assault_score);
            put("rush", R.string.engineer_score);
            put("death_match", R.string.recon_score);
            put("domination", R.string.support_score);
            put("obliteration", R.string.commander_score);
            put("air_superiority", R.string.squad_score);
            put("defuse", R.string.squad_score);

            put("repairs", R.string.assault_score);
            put("revives", R.string.engineer_score);
            put("heals", R.string.recon_score);
            put("resupplies", R.string.support_score);
            put("avenger_kills", R.string.commander_score);
            put("savior_kills", R.string.squad_score);
            put("suppression_assists", R.string.squad_score);
            put("quits", R.string.squad_score);

            put("dogtag_taken", R.string.assault_score);
            put("vehicles_destroyed", R.string.engineer_score);
            put("vehicle_damage", R.string.recon_score);
            put("headshots", R.string.support_score);
            put("longest_headshot", R.string.commander_score);
            put("highest_kill_streak", R.string.squad_score);
            put("nemesis_kills", R.string.squad_score);
            put("highest_nemesis_streak", R.string.squad_score);

            put("flags_captured", R.string.assault_score);
            put("flags_defended", R.string.engineer_score);
        }
    };

    public static int get(String key) {
        return map.containsKey(key) ? map.get(key) : R.string.na;
    }
}
