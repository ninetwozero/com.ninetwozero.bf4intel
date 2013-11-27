package com.ninetwozero.bf4intel.resource.maps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class CompletionStringMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("ID_WEB_COMMON_CAMPAIGN", R.string.overview_campaign);
            put("ID_WEB_HEADER_ASSIGNMENTS", R.string.overview_assignments);
            put("ID_WEB_COMMON_MEDALS", R.string.overview_medals);
            put("ID_WEB_COMMON_RIBBONS", R.string.overview_ribbons);
            put("ID_WEB_PROFILE_WEAPONS", R.string.overview_weapons);
            put("ID_WEB_COMMON_VEHICLE_UNLOCKS", R.string.overview_vehicles);
            put("ID_WEB_COMMON_KITS", R.string.overview_kits);
            put("ID_WEB_HEADER_DOGTAGS", R.string.overview_dogtags);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key)? map.get(key) : R.string.na;
    }
}
