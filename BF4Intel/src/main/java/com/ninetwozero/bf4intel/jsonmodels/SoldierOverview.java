package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SoldierOverview {
    @SerializedName("viewedPersonaInfo")
    private PersonaInfo mPersonaInfo;

    @SerializedName("currentRankNeeded")
    private Rank mCurrentRank;

    @SerializedName("nextRankNeeded")
    private Rank mNextRank;

    @SerializedName("topWeapons")
    private List<BaseStatsModel> mTopWeapons;

    @SerializedName("topVehicles")
    private List<BaseStatsModel> mTopVehicles;

    @SerializedName("gameProgress")
    private List<CompletionProgress> mCompletions;

    @SerializedName("overviewStats")
    private SkillOverview mBasicSoldierStats;

    public PersonaInfo getPersonaInfo() {
        return mPersonaInfo;
    }

    public Rank getCurrentRank() {
        return mCurrentRank;
    }

    public Rank getNextRank() {
        return mNextRank;
    }

    public List<BaseStatsModel> getTopWeapons() { return mTopWeapons; }

    public List<BaseStatsModel> getTopVehicles() {
        return mTopVehicles;
    }

    public List<CompletionProgress> getCompletions() {
        return mCompletions;
    }

    public SkillOverview getBasicSoldierStats() {
        return mBasicSoldierStats;
    }

    public int getMaxScoreCurrentRank() {
        return mNextRank.getPointsNeeded() - mCurrentRank.getPointsNeeded();
    }

    public int getScoreLeftToNextRank() {
        return mNextRank.getPointsNeeded() - mBasicSoldierStats.getScore();
    }
}
