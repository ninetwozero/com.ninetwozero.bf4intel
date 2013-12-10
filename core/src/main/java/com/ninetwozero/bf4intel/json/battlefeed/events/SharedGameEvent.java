package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.SharedGameEventCategory;
import com.ninetwozero.bf4intel.json.battlefeed.SharedGameEventItem;

public class SharedGameEvent extends BaseEvent {
    private String gameId;
    private SharedGameEventCategory category;
    private SharedGameEventItem[] items;

    public SharedGameEvent(final String gameId, final SharedGameEventCategory category, final SharedGameEventItem[] items) {
        this.gameId = gameId;
        this.category = category;
        this.items = items;
    }

    public String getGameId() {
        return gameId;
    }

    public SharedGameEventCategory getCategory() {
        return category;
    }

    public SharedGameEventItem[] getItems() {
        return items;
    }
}
