package com.ninetwozero.bf4intel.dao.unlocks.vehicles;

import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VehicleUnlockMapSorter {
    public static SortedVehicleUnlocks sort(final Map<String, List<VehicleUnlock>> unlockMap) {
        List<VehicleUnlock> list = new ArrayList<VehicleUnlock>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        Collections.sort(list);
        return new SortedVehicleUnlocks(list);
    }
}
