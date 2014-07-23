package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

public abstract class GameReport {

    protected static final int RESULT_DRAW = 0;

    @SerializedName("map")
    private String mapName;
    @SerializedName("winner")
    private int winner;
    @SerializedName("gameMode")
    private long gameMode;
    @SerializedName("gameReportId")
    private long gameReportId;
    @SerializedName("createdAt")
    private long createdAt;
    @SerializedName("duration")
    private int duration;
    @SerializedName("name")
    private String serverName;

    public String getMapName() {
        return mapName;
    }

    public int getWinner() {
        return winner;
    }

    public long getGameMode() {
        return gameMode;
    }

    public long getGameReportId() {
        return gameReportId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public int getDuration() {
        return duration;
    }

    public String getServerName() {
        return serverName;
    }

    public abstract MatchResult findMatchResultFor(int soldierId);
}
