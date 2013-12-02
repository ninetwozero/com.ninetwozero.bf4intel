package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

import com.ninetwozero.bf4intel.datatypes.EventType;

abstract public class BaseEvent {
    protected String eventName;
    protected EventType eventType;

    public BaseEvent() {

    }

    public BaseEvent(final String event, final EventType type) {
        this.eventName = event;
        this.eventType = type;
    }

    public String getEventName() {
        return this.eventName;
    }
}
