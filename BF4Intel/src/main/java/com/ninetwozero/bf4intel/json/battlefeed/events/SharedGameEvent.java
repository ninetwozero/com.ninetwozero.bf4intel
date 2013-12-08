package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.SharedGameEventItem;

public class SharedGameEvent extends BaseEvent {
    private String gameId;
    private String category;
    private SharedGameEventItem[] items;

    public SharedGameEvent(final String gameId, final String category, final SharedGameEventItem[] items) {
        this.gameId = gameId;
        this.category = category;
        this.items = items;
    }

    public String getGameId() {
        return gameId;
    }

    public String getCategory() {
        return category;
    }

    public SharedGameEventItem[] getItems() {
        return items;
    }
}
