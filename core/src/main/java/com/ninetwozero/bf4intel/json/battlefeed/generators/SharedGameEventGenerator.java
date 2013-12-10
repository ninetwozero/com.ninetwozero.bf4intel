package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.SharedGameEventCategory;
import com.ninetwozero.bf4intel.json.battlefeed.SharedGameEventItem;
import com.ninetwozero.bf4intel.json.battlefeed.events.SharedGameEvent;

import java.util.HashMap;
import java.util.Map;

public class SharedGameEventGenerator implements EventGenerator {
    private final String[] trackingObjectNames = {"assignments", "awards", "unlocks"};
    private final Map<String, SharedGameEventCategory> gameEventTypeMap = new HashMap<String, SharedGameEventCategory>() {
        {
            put("BF4ASSIGNMENTS", SharedGameEventCategory.ASSIGNMENTS);
            put("BF4AWARDS", SharedGameEventCategory.AWARDS);
            put("BF4DOGTAGS", SharedGameEventCategory.DOGTAGS);
            put("BF4GAMEREPORT", SharedGameEventCategory.GAME_REPORT);
            put("BF4TRACKINGCOMPLETE", SharedGameEventCategory.TRACKING_COMPLETE);
            put("BF4RANKUP", SharedGameEventCategory.RANKUP);
        }
    };

    // TODO: Can we get rid of manual JSON mess?
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        final String category = jsonObject.get("eventName").getAsString();
        final SharedGameEventCategory type = getTypeForCategory(category);
        final BaseEvent event = new SharedGameEvent(
            jsonObject.get("gameHistoryId").getAsString(),
            type,
            gson.fromJson(getSpecializedJson(jsonObject, category, type), SharedGameEventItem[].class)
        );
        event.setEventType(EventType.SHARED_GAME_EVENT);
        return event;
    }

    private SharedGameEventCategory getTypeForCategory(final String category) {
        return gameEventTypeMap.containsKey(category)? gameEventTypeMap.get(category) : SharedGameEventCategory.UNKNOWN;
    }

    private JsonArray getSpecializedJson(final JsonObject jsonObject, final String category, final SharedGameEventCategory type) {
        final JsonObject baseGameEventObject = jsonObject.get(category).getAsJsonObject();
        switch (type) {
            case ASSIGNMENTS:
            case AWARDS:
            case DOGTAGS:
            case GAME_REPORT:
                return baseGameEventObject.get(type.getArrayKey()).getAsJsonArray();
            case TRACKING_COMPLETE:
                return fetchJsonArrayForTrackingComplete(baseGameEventObject);
            case RANKUP:
                return fetchJsonArrayForRankUp(baseGameEventObject);
            default:
                return new JsonArray();
        }
    }

    private JsonArray fetchJsonArrayForRankUp(final JsonObject baseGameEventObject) {
        JsonArray newArray = new JsonArray();
        newArray.add(baseGameEventObject);
        return newArray;
    }

    private JsonArray fetchJsonArrayForTrackingComplete(final JsonObject baseGameEventObject) {
        for (String key : trackingObjectNames) {
            if (baseGameEventObject.get(key).isJsonArray()) {
                return baseGameEventObject.get(key).getAsJsonArray();
            }
        }
        return new JsonArray();
    }
}
