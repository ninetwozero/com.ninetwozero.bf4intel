package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

import java.util.HashMap;
import java.util.Map;

public class GameAccessEvent extends BaseEvent {

    private static final String GAME_NAME = "Battlefield 4";
    private final Map<Integer, String> expansionMap = new HashMap<Integer, String>() {
        {
            put(1048576, "China Rising");
        }
    };

    @SerializedName("game")
    private int game;
    @SerializedName("expansion")
    private int expansion;
    @SerializedName("platform")
    private int platform;

    public int getGame() {
        return game;
    }

    public int getPlatform() {
        return this.platform;
    }

    public int getExpansion() {
        return expansion;
    }

    public String getFullTitle() {
        final StringBuilder builder = new StringBuilder().append(GAME_NAME);
        if (expansion > 0) {
            builder.append(": ").append(expansionMap.get(expansion));
        }
        return builder.toString();
    }
}
