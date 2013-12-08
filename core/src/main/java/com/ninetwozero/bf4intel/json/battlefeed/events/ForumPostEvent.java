package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class ForumPostEvent extends BaseEvent {
    @SerializedName("postBody")
    private String postBody;
    @SerializedName("threadTitle")
    private String threadTitle;

    public String getPostBody() {
        return postBody;
    }

    public String getThreadTitle() {
        return threadTitle;
    }
}
