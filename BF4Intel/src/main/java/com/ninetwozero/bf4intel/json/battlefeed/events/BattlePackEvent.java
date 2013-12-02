package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class BattlePackEvent extends BaseEvent {

    private final String name;
    private final String packOrigin;
    //private List<>

    public BattlePackEvent(final EventType eventType, final String name, final String packOrigin) {
        super(eventType);
        this.name = name;
        this.packOrigin = packOrigin;
    }

    public String getName() {
        return name;
    }

    public String getPackOrigin() {
        return packOrigin;
    }

    @Override
    public void populateEventSpecificData(final View view) {
        // TODO
    }
}
