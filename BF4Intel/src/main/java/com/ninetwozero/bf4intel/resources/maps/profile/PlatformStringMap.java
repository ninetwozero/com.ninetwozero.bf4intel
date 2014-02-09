package com.ninetwozero.bf4intel.resources.maps.profile;

import java.util.HashMap;
import java.util.Map;

public class PlatformStringMap {
    private static Map<Integer, String> map = new HashMap<Integer, String>() {
        {
            put(1, "PC");
            put(2, "Xbox 360");
            put(4, "PS3");
            put(32, "PS4");
            put(64, "Xbox One");
        }
    };

    public static String get(final int id) {
        return map.containsKey(id)  ? map.get(id) : "...";
    }
}
