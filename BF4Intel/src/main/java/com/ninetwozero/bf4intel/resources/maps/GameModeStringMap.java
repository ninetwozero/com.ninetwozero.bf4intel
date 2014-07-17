package com.ninetwozero.bf4intel.resources.maps;

import java.util.HashMap;
import java.util.Map;

public class GameModeStringMap {
    private static final Map<Long, String> map = new HashMap<Long, String>() {{
        put(1l, "Conquest");
        put(2l, "Rush");
        put(8l, "Squad Deathmatch");
        put(32l, "Team Deathmatch");
        put(64l, "Conquest Large");
        put(1024l, "Domination");
        put(2048l, "Hardcore Team Deathmatch");
        put(524288l, "Capture the Flag");
        put(2097152l, "Obliteration");
        put(8388608l, "Air Superiority");
        put(33554432l, "Carrier Assault");
        put(67108864l, "Carrier Assault Large");
        put(16777216l, "Defuse");
        put(34359738368l, "Chain Link");
    }};

    public static String get(final long id) {
        return map.containsKey(id)? map.get(id) : "N/A";
    }
}
