package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;

import java.util.HashMap;
import java.util.Map;

public class FeedEventLayoutMap {
    private static final Map<EventType, Integer> map = new HashMap<EventType, Integer>() {{
        put(EventType.GAME_ACCESS, R.layout.list_item_feed_gameaccess);
        put(EventType.STATUS_MESSAGE, R.layout.list_item_feed_status);
        put(EventType.ADDED_FAV_SERVER, R.layout.list_item_feed_favorite_server);
        put(EventType.RECEIVED_WALL_POST, R.layout.list_item_feed_wallpost);
    }};

    public static int get(final EventType key) {
        return map.containsKey(key)? map.get(key) : R.layout.list_item_feed_status;
    }
}
