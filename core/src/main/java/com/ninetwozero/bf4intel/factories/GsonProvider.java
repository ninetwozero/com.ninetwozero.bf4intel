package com.ninetwozero.bf4intel.factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.utils.FeedItemDeserializer;

public class GsonProvider {
    private static Gson instance;

    private GsonProvider() {
    }

    public static Gson getInstance() {
        if (instance == null) {
            instance = createNewInstance();
        }
        return instance;
    }

    private static Gson createNewInstance() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(FeedItem.class, new FeedItemDeserializer());
        return builder.create();
    }
}
