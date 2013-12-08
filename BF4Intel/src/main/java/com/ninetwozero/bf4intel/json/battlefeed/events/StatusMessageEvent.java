package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class StatusMessageEvent extends BaseEvent {
    @SerializedName("statusMessage")
    private String message;
    @SerializedName("preview")
    private String preview;

    public String getMessage() {
        return this.message;
    }
    public String getPreview() {
        return this.preview;
    }
    public boolean hasPreview() { return !this.preview.equals("null"); }
}
