package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SoldierOverview {
    public static final int VERSION = 1;

    @SerializedName("viewedPersonaInfo")
    private PersonaInfo personaInfo;

    @SerializedName("currentRankNeeded")
    private Rank currentRank;

    @SerializedName("nextRankNeeded")
    private Rank nextRank;

    @SerializedName("topWeapons")
    private List<BaseStatsModel> topWeapons;

    @SerializedName("topVehicles")
    private List<BaseStatsModel> topVehicles;

    @SerializedName("geolbTopPerformance")
    private List<TopLeaderboardItem> leaderboardItems;

    @SerializedName("gameProgress")
    private List<CompletionProgress> completions;

    @SerializedName("overviewStats")
    private SkillOverview basicSoldierStats;

    @SerializedName("platformInt")
    private int platformId;

    public PersonaInfo getPersonaInfo() {
        return personaInfo;
    }

    public Rank getCurrentRank() {
        return currentRank;
    }

    public Rank getNextRank() {
        return nextRank;
    }

    public List<BaseStatsModel> getTopWeapons() { return topWeapons; }

    public List<BaseStatsModel> getTopVehicles() {
        return topVehicles;
    }

    public List<TopLeaderboardItem> getLeaderboardItems() {
        return leaderboardItems;
    }

    public List<CompletionProgress> getCompletions() {
        return completions;
    }

    public SkillOverview getBasicSoldierStats() {
        return basicSoldierStats;
    }

    public int getPlatformId() {
        return platformId;
    }

    public int getMaxScoreCurrentRank() {
        return nextRank.getPointsNeeded() - currentRank.getPointsNeeded();
    }

    public int getProgressToNextRank() {
        return basicSoldierStats.getRankScore() - currentRank.getPointsNeeded();
    }
}
