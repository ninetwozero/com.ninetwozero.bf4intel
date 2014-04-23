package com.ninetwozero.bf4intel.resources.maps;

import java.util.HashMap;
import java.util.Map;

public class GameModeStringMap {
    private static final Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(1, "Conquest");
        put(2, "Rush");
        put(8, "Squad Deathmatch");
        put(32, "Team Deathmatch");
        put(64, "Conquest Large");
        put(1024, "Domination");
        put(2048, "Hardcore Team Deathmatch");
        put(524288, "Capture the Flag");
        put(2097152, "Obliteration");
        put(8388608, "Air Superiority");
        put(33554432, "Carrier Assault");
        put(67108864, "Carrier Assault Large");
        put(16777216, "Defuse");
    }};

    public static String get(final int id) {
        return map.containsKey(id)? map.get(id) : "N/A";
    }
}
