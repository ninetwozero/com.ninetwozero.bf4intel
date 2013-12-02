package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.FavoriteServerEvent;

public class FavoriteServerEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new FavoriteServerEvent(
            EventType.ADDEDFAVSERVER,
            jsonObject.get("serverName").getAsString(),
            jsonObject.get("serverGuid").getAsString()
        );
    }
}
