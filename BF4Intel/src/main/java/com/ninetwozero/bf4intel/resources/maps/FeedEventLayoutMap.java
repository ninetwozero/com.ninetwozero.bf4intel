package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;

import java.util.HashMap;
import java.util.Map;

public class FeedEventLayoutMap {
    private static final Map<EventType, Integer> map = new HashMap<EventType, Integer>() {{
        put(EventType.GAMEACCESS, R.layout.list_item_feed_gameaccess);
        put(EventType.STATUSMESSAGE, R.layout.list_item_feed_status);
        put(EventType.ADDEDFAVSERVER, R.layout.list_item_feed_favorite_server);
        put(EventType.RECEIVEDWALLPOST, R.layout.list_item_feed_wallpost);
    }};

    public static int get(final EventType key) {
        return map.containsKey(key)? map.get(key) : R.layout.list_item_feed_status;
    }
}
