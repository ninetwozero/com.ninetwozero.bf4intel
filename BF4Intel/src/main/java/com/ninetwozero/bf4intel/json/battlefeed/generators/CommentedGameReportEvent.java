package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CommentedGameReportEvent implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new com.ninetwozero.bf4intel.json.battlefeed.events.CommentedGameReportEvent(
            EventType.COMMENTED_GAME_REPORT,
            jsonObject.get("gameReportHistoryId").getAsString(),
            jsonObject.get("serverName").getAsString(),
            jsonObject.get("map").getAsString(),
            jsonObject.get("mapVariant").getAsInt(),
            jsonObject.get("gameMode").getAsInt(),
            jsonObject.get("gameReportComment").getAsString()
        );
    }
}
