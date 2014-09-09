package com.ninetwozero.bf4intel.json.stats.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedStatsGroup {
    public static final String MULTIPLAYER_SCORES = "multiplayer_scores";
    public static final String GENERAL_SCORES = "general_scores";
    public static final String GAME_MODE_SCORES = "game_mode_scores";
    public static final String TEAM_SCORES = "team_scores";
    public static final String EXTRA_SCORES = "extra_scores";
    public static final String GAME_MODE_EXTRA_SCORES = "game_mode_extra_scores";

    @SerializedName("key")
    private String key;
    @SerializedName("stats")
    private List<DetailedStatsItem> stats;

    public DetailedStatsGroup(final String key, final List<DetailedStatsItem> stats) {
        this.key = key;
        this.stats = stats;
    }

    public String getKey() {
        return key;
    }

    public List<DetailedStatsItem> getStats() {
        return stats;
    }
}
