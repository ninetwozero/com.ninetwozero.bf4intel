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

            put("kills", R.string.kills);
            put("deaths", R.string.deaths);
            put("kill_assists", R.string.kill_assists);
            put("kd_ratio", R.string.kd_ratio);
            put("wins", R.string.wins);
            put("losses", R.string.losses);
            put("shots_fired", R.string.shots_fired);
            put("shots_hits", R.string.shots_hits);
            put("accuracy", R.string.accuracy);

            put("conquest", R.string.conquest);
            put("rush", R.string.rush);
            put("death_match", R.string.death_match);
            put("domination", R.string.domination);
            put("capture_the_flag", R.string.capture_the_flag);
            put("obliteration", R.string.obliteration);
            put("air_superiority", R.string.air_superiority);
            put("defuse", R.string.defuse);

            put("repairs", R.string.repairs);
            put("revives", R.string.revives);
            put("heals", R.string.heals);
            put("resupplies", R.string.resupplies);
            put("avenger_kills", R.string.avenger_kills);
            put("savior_kills", R.string.savior_kills);
            put("suppression_assists", R.string.suppression_assists);
            put("quits", R.string.quits);

            put("dogtag_taken", R.string.dogtag_taken);
            put("vehicles_destroyed", R.string.vehicles_destroyed);
            put("vehicle_damage", R.string.vehicle_damage);
            put("headshots", R.string.headshots);
            put("longest_headshot", R.string.longest_headshot);
            put("highest_kill_streak", R.string.highest_kill_streak);
            put("nemesis_kills", R.string.nemesis_kills);
            put("highest_nemesis_streak", R.string.highest_nemesis_streak);

            put("flags_captured", R.string.flags_captured);
            put("flags_defended", R.string.flags_defended);
        }
    };

    public static int get(String key) {
        return map.containsKey(key) ? map.get(key) : R.string.na;
    }
}
