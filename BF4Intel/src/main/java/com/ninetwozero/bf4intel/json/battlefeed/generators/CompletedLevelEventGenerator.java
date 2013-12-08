package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;
import com.ninetwozero.bf4intel.json.battlefeed.events.CompletedLevelEvent;

public class CompletedLevelEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new CompletedLevelEvent(
            EventType.LEVEL_COMPLETE,
            jsonObject.get("level").getAsString(),
            jsonObject.get("gameType").getAsInt(),
            jsonObject.get("difficulty").getAsString(),
            gson.fromJson(jsonObject.get("friend"), Profile.class)
        );
    }
}
