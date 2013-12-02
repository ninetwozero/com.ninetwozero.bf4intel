package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

import android.view.View;

import com.ninetwozero.bf4intel.datatypes.EventType;

abstract public class BaseEvent {
    private final EventType eventType;

    public BaseEvent(final EventType type) {
        this.eventType = type;
    }

    public final EventType getEventType() {
        return this.eventType;
    }

    public abstract void populateEventSpecificData(final View view);
}
