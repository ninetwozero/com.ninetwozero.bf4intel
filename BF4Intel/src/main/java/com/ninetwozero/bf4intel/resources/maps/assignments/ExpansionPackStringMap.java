package com.ninetwozero.bf4intel.resources.maps.assignments;

import android.util.Log;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class ExpansionPackStringMap {

    private static final Map<String, Integer> expansionResources = new HashMap<String, Integer>() {
        {
            put("xp0", R.string.expansion_xp0);
            put("xp1", R.string.expansion_xp1);
            put("xp2", R.string.expansion_xp2);
            put("ghost1", R.string.expansion_xp1);
            put("ghost2", R.string.expansion_xp2);
        }
    };

    public static int get(String expansionID) {
        Log.d("YOLO", "expansionID => " + expansionID);
        return expansionResources.containsKey(expansionID) ? expansionResources.get(expansionID) : R.string.na;
    }
}
