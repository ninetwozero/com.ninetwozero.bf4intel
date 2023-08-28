package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CompletedLevelEvent extends BaseEvent {
    @SerializedName("level")
    private String levelName;
    @SerializedName("gameType")
    private int gameType;
    @SerializedName("difficulty")
    private String difficulty;
    @SerializedName("friend")
    private Profile partner;

    public String getLevelName() {
        return levelName;
    }

    public int getGameType() {
        return gameType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Profile getPartner() {
        return partner;
    }

    public boolean hasPartner() {
        return partner != null;
    }
}
