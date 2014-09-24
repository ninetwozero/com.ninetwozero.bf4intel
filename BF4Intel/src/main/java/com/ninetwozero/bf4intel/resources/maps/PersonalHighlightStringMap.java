package com.ninetwozero.bf4intel.resources.maps;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PersonalHighlightStringMap {
    private static final Map<String, String> map = new HashMap<String, String>() {{
        put("ASSAULT", "Best Assault");
        put("ENGINEER", "Best Engineer");
        put("MEDIC", "Best Medic");
        put("RECON", "Best Recon");
        put("COMMANDER", "Top Commander");
        put("AVENGERKILLS", "The Avenger");
        put("ACCURACY", "Aim King");
        put("HEALS", "Master Healer");
        put("AWARDSCORE", "Highest Awarded");
        put("KILLS", "Killing Machine");
        put("SCOUTHELICOPTERKILLS", "Scout Helicopter Expert");
        put("HEADSHOTS", "Bullseye King");
    }};

    public static String get(final String key) {
        if (key == null) {
            return "N/A";
        }
        return map.containsKey(key.toUpperCase(Locale.getDefault())) ? map.get(key.toUpperCase(Locale.getDefault())) : key;
    }
}
