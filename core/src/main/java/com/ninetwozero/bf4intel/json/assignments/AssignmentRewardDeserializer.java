package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.*;
import com.ninetwozero.bf4intel.json.UnlockType;

import java.lang.reflect.Type;
import java.util.Map;

public class AssignmentRewardDeserializer implements JsonDeserializer<AssignmentReward> {
    @Override
    public AssignmentReward deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!jsonObject.has("nameSID")) {
            jsonObject = findValidUnlockObject(jsonObject);
        }

        final AssignmentReward reward = new Gson().fromJson(jsonObject, AssignmentReward.class);
        reward.setUnlockType(UnlockType.from(reward.getUnlockTypeString()));
        return reward;
    }

    private JsonObject findValidUnlockObject(JsonObject jsonObject) {
        for (Map.Entry<String, JsonElement> jsonEntry : jsonObject.entrySet()) {
            final JsonElement unlockMapItem = jsonEntry.getValue();
            if (unlockMapItem.isJsonNull()) {
                continue;
            }

            final JsonObject newObject = unlockMapItem.getAsJsonObject();
            newObject.addProperty("unlockTypeString", jsonEntry.getKey());
            return unlockMapItem.getAsJsonObject();
        }
        throw new IllegalStateException("No unlock data found in the JSON");
    }
}
