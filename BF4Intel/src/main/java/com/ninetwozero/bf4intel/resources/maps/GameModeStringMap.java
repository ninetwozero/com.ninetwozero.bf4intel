package com.ninetwozero.bf4intel.resources.maps;

import java.util.HashMap;
import java.util.Map;

public class GameModeStringMap {
    private static final Map<Long, String> map = new HashMap<Long, String>() {{
        put(1L, "Conquest");
        put(2L, "Rush");
        put(8L, "Squad Deathmatch");
        put(32L, "Team Deathmatch");
        put(64L, "Conquest Large");
        put(1024L, "Domination");
        put(2048L, "Hardcore Team Deathmatch");
        put(524288L, "Capture the Flag");
        put(2097152L, "Obliteration");
        put(8388608L, "Air Superiority");
        put(33554432L, "Carrier Assault");
        put(67108864L, "Carrier Assault Large");
        put(16777216L, "Defuse");
        put(34359738368L, "Chain Link");
    }};

    public static String get(final long id) {
        return map.containsKey(id)? map.get(id) : "N/A";
    }
}
