package com.ninetwozero.bf4intel.utils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ninetwozero.bf4intel.factories.FeedEventFactory;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

import java.lang.reflect.Type;

public class FeedItemDeserializer implements JsonDeserializer<FeedItem> {
    @Override
    public FeedItem deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final Gson gson = new Gson();
        final JsonObject jsonObject = json.getAsJsonObject();
        final String eventString = jsonObject.get("event").getAsString();
        final JsonObject specialObject = jsonObject.getAsJsonObject(eventString.toUpperCase());
        final JsonElement owner2Element = jsonObject.get("owner2");

        return new FeedItem(
            jsonObject.get("id").getAsString(),
            FeedEventFactory.create(gson, eventString, specialObject),
            eventString,
            jsonObject.get("ownerId").getAsString(),
            gson.fromJson(jsonObject.getAsJsonObject("owner"), Profile.class),
            owner2Element.isJsonNull()? null : gson.fromJson(owner2Element.getAsJsonObject(), Profile.class),
            jsonObject.get("numLikes").getAsInt(),
            jsonObject.get("numComments").getAsInt(),
            jsonObject.get("creationDate").getAsLong(),
            jsonObject.get("itemId").getAsString()
        );
    }
}
