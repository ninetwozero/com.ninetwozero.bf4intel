package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class SoldierOverview {
    @SerializedName("viewedPersonaInfo")
    private PersonaInfo mPersonaInfo;

    @SerializedName("currentRankNeeded")
    private Rank mCurrentRank;

    @SerializedName("nextRankNeeded")
    private Rank mNextRank;

    @SerializedName("kitScores")
    private Map<Integer, Integer> mKitScores;

    @SerializedName("topWeapons")
    private List<WeaponStats> mTopWeapons;

    @SerializedName("topVehicles")
    private List<VehicleStats> mTopVehicles;

    @SerializedName("gameProgress")
    private List<CompletionProgress> mCompletions;

    public SoldierOverview(
        final PersonaInfo personaInfo, final Rank currentRank, final Rank nextRank, final Map<Integer, Integer> kitScores,
        final List<WeaponStats> topWeapons, final List<VehicleStats> topVehicles, final List<CompletionProgress> completions
    ) {
        mPersonaInfo = personaInfo;
        mCurrentRank = currentRank;
        mNextRank = nextRank;
        mKitScores = kitScores;
        mTopWeapons = topWeapons;
        mTopVehicles = topVehicles;
        mCompletions = completions;
    }

    public PersonaInfo getPersonaInfo() {
        return mPersonaInfo;
    }

    public Rank getCurrentRank() {
        return mCurrentRank;
    }

    public Rank getNextRank() {
        return mNextRank;
    }

    public Map<Integer, Integer> getKitScores() {
        return mKitScores;
    }

    public List<WeaponStats> getTopWeapons() {
        return mTopWeapons;
    }

    public List<VehicleStats> getTopVehicles() {
        return mTopVehicles;
    }

    public List<CompletionProgress> getCompletions() {
        return mCompletions;
    }

    public int getMaxScoreCurrentRank() {
        return mNextRank.getPointsNeeded() - mCurrentRank.getPointsNeeded();
    }
}
