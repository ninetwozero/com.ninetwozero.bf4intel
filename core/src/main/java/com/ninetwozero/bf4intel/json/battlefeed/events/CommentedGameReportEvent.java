package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CommentedGameReportEvent extends BaseEvent {
    @SerializedName("gameReportHistoryId")
    private String id;
    @SerializedName("serverName")
    private String server;
    @SerializedName("map")
    private String map;
    @SerializedName("mapVariant")
    private int mapVariant;
    @SerializedName("gameMode")
    private long gameMode;
    @SerializedName("gameReportComment")
    private String comment;

    public String getId() {
        return id;
    }

    public String getServer() {
        return server;
    }

    public String getMap() {
        return map;
    }

    public int getMapVariant() {
        return mapVariant;
    }

    public long getGameMode() {
        return gameMode;
    }

    public String getComment() {
        return comment;
    }
}
