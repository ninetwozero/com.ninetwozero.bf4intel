package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

public class WallpostEvent extends BaseEvent {
    private Profile sender;
    private String message;

    public WallpostEvent(final EventType eventType, final Profile sender, final String message) {
        super(eventType);
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public Profile getSender() {
        return this.sender;
    }
}
