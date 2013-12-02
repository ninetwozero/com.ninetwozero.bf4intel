package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameAccessEvent;

public class GameAccessEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        final JsonElement element = jsonObject.get("platform");
        return new GameAccessEvent(
            EventType.GAMEACCESS,
            jsonObject.get("game").getAsInt(),
            element.isJsonNull()? 1 : element.getAsInt(),
            jsonObject.get("expansion").getAsInt()
        );
    }
}
