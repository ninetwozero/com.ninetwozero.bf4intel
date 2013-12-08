package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.CreatedForumThreadEvent;

public class CreatedForumThreadEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        // TODO: Worth mentioning: itemId in parent JSON is actually ID to the thread
        return new CreatedForumThreadEvent(
            EventType.CREATED_FORUM_THREAD,
            jsonObject.get("threadTitle").getAsString(),
            jsonObject.get("threadBody").getAsString()
        );
    }
}
