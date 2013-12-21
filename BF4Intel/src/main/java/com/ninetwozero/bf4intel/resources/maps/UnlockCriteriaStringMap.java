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
            put("sc_vehicleajet", R.string.unlock_sc_vehicleajet);
            put("sc_vehiclesjet", R.string.unlock_sc_vehiclesjet);
            put("sc_vehiclembt", R.string.unlock_sc_vehiclembt);
            put("sc_vehiclesh", R.string.unlock_sc_vehiclesh);
            put("sc_sniperrifles", R.string.unlock_sc_sniperrifles);
            put("sc_lmgs", R.string.unlock_sc_lmgs);
            put("sc_dmrs", R.string.unlock_sc_dmrs);
            put("sc_pdws", R.string.unlock_sc_pdws);
            put("sc_handgrenades", R.string.unlock_sc_handgrenades);
            put("sc_handguns", R.string.unlock_sc_handguns);
            put("sc_carbines", R.string.unlock_sc_carbines);
            put("sc_shotguns", R.string.unlock_sc_shotguns);
            put("sc_assaultrifles", R.string.unlock_sc_assaultrifles);
        }
    };

    public static int get(final String key) {
        return map.containsKey(key) ? map.get(key) : R.string.empty;
    }
}
