package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.json.battlefeed.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedGameReportEvent;

public class CommentedGameReportEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        BaseEvent event = gson.fromJson(jsonObject, CommentedGameReportEvent.class);
        event.setEventType(EventType.COMMENTED_GAME_REPORT);
        return event;
    }
}
