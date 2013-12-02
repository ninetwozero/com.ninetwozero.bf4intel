package com.ninetwozero.bf4intel.jsonmodels.battlefeed.events;

import android.view.View;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;

public class UnknownEvent extends BaseEvent {
    public UnknownEvent(final EventType eventType) {
        super(eventType);
    }

    @Override
    public void populateEventSpecificData(final View view) {
        // TODO
    }
}
