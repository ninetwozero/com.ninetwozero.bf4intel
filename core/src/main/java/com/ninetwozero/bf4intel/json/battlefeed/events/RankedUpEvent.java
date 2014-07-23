package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class RankedUpEvent extends BaseEvent {
    @SerializedName("nameSID")
    private String name;
    @SerializedName("rank")
    private int rank;
    @SerializedName("gameHistoryId")
    private String gameHistoryId;

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public String getGameHistoryId() {
        return gameHistoryId;
    }
}
