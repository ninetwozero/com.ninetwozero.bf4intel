package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class BattlePackEvent extends BaseEvent {

    private final String name;
    private final String packKey;
    //private List<>

    public BattlePackEvent(final EventType eventType, final String name, final String packKey) {
        super(eventType);
        this.name = name;
        this.packKey = packKey;
    }

    public String getName() {
        return name;
    }

    public String getPackKey() {
        return packKey;
    }

    @Override
    public void populateEventSpecificData(final View view) {
        // TODO
    }
}
