package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class FavoriteServerEvent extends BaseEvent {
    @SerializedName("serverName")
    private String serverName;
    @SerializedName("serverGuid")
    private String serverGuid;

    public String getServerName() {
        return serverName;
    }

    public String getServerGuid() {
        return serverGuid;
    }
}
