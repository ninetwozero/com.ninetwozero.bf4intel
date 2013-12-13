package com.ninetwozero.bf4intel.json.battlefeed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninetwozero.bf4intel.util.IntelJsonParser;
import com.ninetwozero.bf4intel.utils.FeedItemDeserializer;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BattleFeedTest {
    private static BattleFeed battleFeed;
    private static final List<EventType> validTypes = new ArrayList<EventType>() {{
        add(EventType.LEVEL_COMPLETE);
        add(EventType.ADDED_FAV_SERVER);
        add(EventType.SHARED_GAME_EVENT);
        add(EventType.SHARED_GAME_EVENT);
        add(EventType.GAME_REPORT);
        add(EventType.BECAME_FRIENDS);
        add(EventType.GAME_ACCESS);
        add(EventType.RECEIVED_WALL_POST);
        add(EventType.COMMENTED_GAME_REPORT);
        add(EventType.RANKED_UP);
        add(EventType.STATUS_MESSAGE);
        add(EventType.BATTLE_PACK);
        add(EventType.SHARED_GAME_EVENT);
        add(EventType.WROTE_FORUM_POST);
        add(EventType.CREATED_FORUM_THREAD);
        add(EventType.COMMENTED_BLOG);
    }};

    @BeforeClass
    public static void setup() throws IOException {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FeedItem.class, new FeedItemDeserializer());
        final Gson gson = gsonBuilder.create();
        battleFeed = IntelJsonParser.parse(
            "/json/battlefeed.json",
            BattleFeed.class,
            gson
        );
    }

    @Test
    public void testThatWeHaveCorrectFeedItemTypesinList() throws IOException {
        for (int i = 0, max = battleFeed.getFeedItems().size(); i < max; i++) {
            // TODO: Remove when finished debugging
            System.out.println("LOOP#" + i);
            System.out.println("get(i) => " + battleFeed.getFeedItems().get(i));
            System.out.println("getEvent() => " + battleFeed.getFeedItems().get(i).getEvent());
            System.out.println("getEventType() => " + battleFeed.getFeedItems().get(i).getEvent().getEventType());
            /*
            TODO: LOOP#7: get(i) => null
             */
            assertEquals(
                validTypes.get(i),
                battleFeed.getFeedItems().get(i).getEvent().getEventType()
            );
        }
    }
}
