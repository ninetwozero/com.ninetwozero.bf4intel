package com.ninetwozero.bf4intel.json.battlefeed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninetwozero.bf4intel.util.IntelJsonParser;
import com.ninetwozero.bf4intel.utils.FeedItemDeserializer;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class BattleFeedTest {

    @Test
    public void should_parse_Json() throws IOException {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FeedItem.class, new FeedItemDeserializer());
        final Gson gson = gsonBuilder.create();
        final BattleFeed battleFeed = IntelJsonParser.parse(
            "/json/battlefeed.json",
            BattleFeed.class,
            gson
        );

        assertEquals(battleFeed.getUserId(), "2832658801548551060");
        assertEquals(battleFeed.getFeedItems().size(), 10);
        assertEquals(
            battleFeed.getFeedItems().get(0).getEvent().getEventType(),
            EventType.LEVEL_COMPLETE
        );
    }
}
