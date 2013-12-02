package com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.CreatedForumThreadEvent;

public class CreatedForumThreadEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        // TODO: Worth mentioning: itemId in parent JSON is actually ID to the thread
        return new CreatedForumThreadEvent(
            EventType.CREATEDFORUMTHREAD,
            jsonObject.get("threadTitle").getAsString(),
            jsonObject.get("threadBody").getAsString()
        );
    }
}
