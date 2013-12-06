package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class FavoriteServerEvent extends BaseEvent {
    private final String serverName;
    private final String serverGuid;

    public FavoriteServerEvent(final EventType type, final String serverName, final String serverGuid) {
        super(type);
        this.serverName = serverName;
        this.serverGuid = serverGuid;
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerGuid() {
        return serverGuid;
    }
}
