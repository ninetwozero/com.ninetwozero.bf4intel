package com.ninetwozero.bf4intel.jsonmodels.battlefeed.events;

import android.view.View;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;

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

    @Override
    public void populateEventSpecificData(final View view) {
        // TODO
    }
}
