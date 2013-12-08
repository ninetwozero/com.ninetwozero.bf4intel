package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

public class WallpostEvent extends BaseEvent {
    @SerializedName("writerUser")
    private Profile sender;
    @SerializedName("wallBody")
    private String message;

    public String getMessage() {
        return this.message;
    }

    public Profile getSender() {
        return this.sender;
    }
}
