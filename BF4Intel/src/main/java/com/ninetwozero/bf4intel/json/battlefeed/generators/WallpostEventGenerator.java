package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;
import com.ninetwozero.bf4intel.json.battlefeed.events.WallpostEvent;

public class WallpostEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new WallpostEvent(
            EventType.RECEIVEDWALLPOST,
            gson.fromJson(jsonObject.get("writerUser"), Profile.class),
            jsonObject.get("wallBody").getAsString()
        );
    }
}
