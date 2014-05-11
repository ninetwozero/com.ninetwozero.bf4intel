package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class AssignmentPrerequisiteDeserializer implements JsonDeserializer<AssignmentPrerequisite> {
    @Override
    public AssignmentPrerequisite deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has("award")) {
            final JsonObject awardObject = jsonObject.getAsJsonObject("award");
            return new AssignmentPrerequisite(
                awardObject.get("stringID").getAsString(),
                awardObject.get("code").getAsString(),
                jsonObject.get("group").getAsString(),
                jsonObject.get("timesTaken").getAsInt()
            );
        } else {
            return new Gson().fromJson(jsonObject, AssignmentPrerequisite.class);
        }
    }
}