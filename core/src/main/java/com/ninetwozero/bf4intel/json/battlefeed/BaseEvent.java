package com.ninetwozero.bf4intel.json.battlefeed;

abstract public class BaseEvent {
    private EventType eventType;

    public final EventType getEventType() {
        return this.eventType;
    }
    public final void setEventType(final EventType eventType) {
        this.eventType = eventType;
    }
}
