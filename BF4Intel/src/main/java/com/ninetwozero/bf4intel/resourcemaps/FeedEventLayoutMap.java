package com.ninetwozero.bf4intel.resourcemaps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class FeedEventLayoutMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {{
        put("gameaccess", R.layout.list_item_feed_gameaccess);
        put("statusmessage", R.layout.list_item_feed_status);
    }};

    public static int get(final String key) {
        return map.containsKey(key)? map.get(key) : android.R.layout.activity_list_item;
    }
}
