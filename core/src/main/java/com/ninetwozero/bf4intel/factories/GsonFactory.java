package com.ninetwozero.bf4intel.factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.utils.FeedItemDeserializer;
import com.ninetwozero.bf4intel.utils.SummarizedSoldierStatsDeserializer;

public class GsonFactory {
    private static Gson instance;

    public static Gson getInstance() {
        if (instance == null) {
            instance = createNewInstance();
        }
        return instance;
    }

    private static Gson createNewInstance() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(FeedItem.class, new FeedItemDeserializer());
        builder.registerTypeAdapter(SummarizedSoldierStats.class, new SummarizedSoldierStatsDeserializer());
        return builder.create();
    }
}
