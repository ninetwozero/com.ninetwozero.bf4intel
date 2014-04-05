package com.ninetwozero.bf4intel.dao.unlocks.vehicles;

import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleUnlockMapSorter {
    public static SortedVehicleUnlocks sort(final Map<String, List<VehicleUnlock>> unlockMap) {
        final Map<String, List<VehicleUnlock>> map = new HashMap<String, List<VehicleUnlock>>();
        for (String key : unlockMap.keySet()) {
            final List<VehicleUnlock> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return new SortedVehicleUnlocks(map);
    }
}
