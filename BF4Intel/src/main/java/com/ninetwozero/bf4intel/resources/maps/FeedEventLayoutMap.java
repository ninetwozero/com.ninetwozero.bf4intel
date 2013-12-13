package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.battlefeed.EventType;

import java.util.HashMap;
import java.util.Map;

public class FeedEventLayoutMap {
    private static final Map<EventType, Integer> map = new HashMap<EventType, Integer>() {{
        put(EventType.STATUS_MESSAGE, R.layout.list_item_feed_status);
        put(EventType.BECAME_FRIENDS, R.layout.list_item_feed_friendship);
        put(EventType.CREATED_FORUM_THREAD, R.layout.list_item_feed_forum);
        put(EventType.WROTE_FORUM_POST, R.layout.list_item_feed_forum);
        put(EventType.RECEIVED_WALL_POST, R.layout.list_item_feed_wallpost);
        put(EventType.ADDED_FAV_SERVER, R.layout.list_item_feed_favorite_server);
        put(EventType.LEVEL_COMPLETE, R.layout.list_item_feed_levelcomplete);
        put(EventType.GAME_REPORT, R.layout.list_item_feed_battlereport);
        put(EventType.COMMENTED_GAME_REPORT, R.layout.list_item_feed_gamecomment);
        put(EventType.COMMENTED_BLOG, R.layout.list_item_feed_blogcomment);
        put(EventType.GAME_ACCESS, R.layout.list_item_feed_gameaccess);
        put(EventType.SHARED_GAME_EVENT, R.layout.list_item_feed_sharedgameevent);
        put(EventType.BATTLE_PACK, R.layout.list_item_feed_battlepack);
        put(EventType.RANKED_UP, R.layout.list_item_feed_rankedup);
        put(EventType.UNKNOWN, R.layout.list_item_feed_status);
    }};

    public static int get(final EventType key) {
        return map.containsKey(key)? map.get(key) : R.layout.list_item_feed_status;
    }
}
