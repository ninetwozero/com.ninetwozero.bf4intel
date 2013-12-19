package com.ninetwozero.bf4intel.resources.maps;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

public class UnlockCriteriaStringMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("sc_vehicleaa", R.string.unlock_sc_vehicleaa);
            put("sc_vehicleaboat", R.string.unlock_sc_vehicleaboat);
            put("sc_vehicleah", R.string.unlock_sc_vehicleah);
            put("sc_vehicleifv", R.string.unlock_sc_vehicleifv);
            put("sc_vehiclejet", R.string.unlock_sc_vehiclejet);
            put("sc_vehiclembt", R.string.unlock_sc_vehiclembt);
            put("sc_vehiclesh", R.string.unlock_sc_vehiclesh);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key) ? map.get(key) : R.string.na;
    }
}
