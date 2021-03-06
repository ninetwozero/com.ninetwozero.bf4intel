package com.ninetwozero.bf4intel.resources.maps.assignments;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class ExpansionIconsImageMap {

    private static final Map<String, Integer> expansionResources = new HashMap<String, Integer>() {
        {
            put("xp0", R.drawable.icon_xp0);
            put("xp1", R.drawable.icon_xp1);
            put("xp2", R.drawable.icon_xp2);
            put("xp3", R.drawable.icon_xp3);
            put("xp4", R.drawable.icon_xp4);
            put("ghost1", R.drawable.icon_xp1);
            put("ghost2", R.drawable.icon_xp2);
            put("ghost3", R.drawable.icon_xp3);
        }
    };

    public static int get(String expansionID) {
        return expansionResources.containsKey(expansionID) ? expansionResources.get(expansionID) : R.drawable.empty;
    }
}
