package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CreatedForumThreadEvent extends BaseEvent {
    @SerializedName("threadTitle")
    private String title;
    @SerializedName("threadBody")
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
