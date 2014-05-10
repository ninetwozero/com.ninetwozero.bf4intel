package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

public class AssignmentCriteriaDeserializer implements JsonDeserializer<AssignmentCriteria> {
    private static final String SUBOBJECT_KEY = "originalCriteria";

    @Override
    public AssignmentCriteria deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has(SUBOBJECT_KEY)) {
            return createAssignmentCriteriaFromApi(jsonObject);
        } else {
            return new Gson().fromJson(jsonObject, AssignmentCriteria.class);
        }
    }

    private AssignmentCriteria createAssignmentCriteriaFromApi(JsonObject jsonObject) {
        final JsonObject subCriteriaObject = jsonObject.get(SUBOBJECT_KEY).getAsJsonObject();
        return new AssignmentCriteria(
            subCriteriaObject.get("descriptionID").getAsString(),
            jsonObject.get("actualValue").getAsInt(),
            subCriteriaObject.get("completionValue").getAsInt(),
            jsonObject.get("completion").getAsInt(),
            subCriteriaObject.get("criteriaType").getAsString()
        );
    }
}