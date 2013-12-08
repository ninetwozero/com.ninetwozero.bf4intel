package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.SharedGameEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.SharedGameEventItem;

import java.util.HashMap;
import java.util.Map;

public class SharedGameEventGenerator implements EventGenerator {
    private Map<String, String> subObjectMap = new HashMap<String, String>() {{
        put("BF4AWARDS", "statItems");
        put("BF4ASSIGNMENTS", "statItems");
        put("BF4DOGTAGS", "dogtags");
        put("BF4GAMEREPORT", "statItems");
    }};

    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        final String category = jsonObject.get("eventName").getAsString();
        return new SharedGameEvent(
            EventType.SHARED_GAME_EVENT,
            jsonObject.get("gameHistoryId").getAsString(),
            category,
            gson.fromJson(getSpecializedJson(jsonObject, category), SharedGameEventItem[].class)
        );
    }

    private JsonArray getSpecializedJson(final JsonObject jsonObject, final String category) {
        return jsonObject.get(category).getAsJsonObject().get(subObjectMap.get(category)).getAsJsonArray();
    }
}
