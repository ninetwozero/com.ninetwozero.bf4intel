package com.ninetwozero.bf4intel.soldieroverview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SoldierOverview {
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

    @SerializedName("gameProgress")
    private List<CompletionProgress> completions;

    @SerializedName("overviewStats")
    private SkillOverview basicSoldierStats;

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

    public List<CompletionProgress> getCompletions() {
        return completions;
    }

    public SkillOverview getBasicSoldierStats() {
        return basicSoldierStats;
    }

    public int getMaxScoreCurrentRank() {
        return nextRank.getPointsNeeded() - currentRank.getPointsNeeded();
    }

    public int getScoreLeftToNextRank() {
        return nextRank.getPointsNeeded() - basicSoldierStats.getScore();
    }
}
