package com.ninetwozero.bf4intel.utils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.ninetwozero.bf4intel.json.login.Persona;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;

import java.lang.reflect.Type;

public class SummarizedSoldierStatsDeserializer implements JsonDeserializer<SummarizedSoldierStats> {
    @Override
    public SummarizedSoldierStats deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final Gson gson = new Gson();
        final SummarizedSoldierStats object = gson.fromJson(json, typeOfT);
        final Persona persona = gson.fromJson(json.getAsJsonObject().get("persona"), Persona.class);

        object.setPersonaId(persona.getPersonaId());
        object.setPersonaName(persona.getPersonaName());
        object.setPicture(persona.getPicture());
        object.setTag(persona.getTag());
        object.setUserId(persona.getUserId());

        return object;
    }
}
