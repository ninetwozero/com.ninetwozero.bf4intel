package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

public abstract class GameReports {

    @SerializedName("map")
    private String mapName;
    @SerializedName("winner")
    private int winner;
    @SerializedName("gameReportId")
    private long gameReportId;
    @SerializedName("createdAt")
    private long createdAt;
    @SerializedName("duration")
    private long duration;
    @SerializedName("name")
    private String serverName;

    public String getMapName() {
        return mapName;
    }

    public int getWinner() {
        return winner;
    }

    public long getGameReportId() {
        return gameReportId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getDuration() {
        return duration;
    }

    public String getServerName() {
        return serverName;
    }

    public abstract boolean isWinner();
}
