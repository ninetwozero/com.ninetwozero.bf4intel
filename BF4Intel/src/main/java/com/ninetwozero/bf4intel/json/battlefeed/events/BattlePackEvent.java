package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BattlePackItem;

public class BattlePackEvent extends BaseEvent {
    private final String name;
    private final String packKey;
    private final BattlePackItem[] items;

    public BattlePackEvent(final EventType eventType, final String nameSID, final String packKey, final BattlePackItem[] items) {
        super(eventType);
        this.name = nameSID;
        this.packKey = packKey;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getPackKey() {
        return packKey;
    }

    public BattlePackItem[] getItems() {
        return items;
    }

    public String fetchPackType() {
        if (name.contains("WEAPON")) {
            return "WEAPON";
        } else {
            return name.substring(name.lastIndexOf('_')+1);
        }
    }
}
