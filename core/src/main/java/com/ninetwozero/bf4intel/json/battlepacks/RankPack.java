package com.ninetwozero.bf4intel.json.battlepacks;

import com.google.gson.annotations.SerializedName;

public class RankPack {
    @SerializedName("deltaPointsNeeded")
    private long totalPointsNeeded;
    @SerializedName("deltaPointsNeeded")
    private long pointsNeeded;
    @SerializedName("rank")
    private int nextRank;

    public long getTotalPointsNeeded() {
        return totalPointsNeeded;
    }

    public long getPointsNeeded() {
        return pointsNeeded;
    }

    public int getNextRank() {
        return nextRank;
    }
}
