package com.ninetwozero.bf4intel.database.dao.unlocks.vehicles;

import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VehicleUnlockMapSorter {
    private static final String[] CATEGORY_ORDER = new String[] {
        "Vehicle Air Helicopter Scout", "Vehicle Air Jet Attack",
        "Vehicle Fast Attack Craft", "Vehicle Infantry Fighting Vehicle",
        "Vehicle Air Jet Stealth", "Vehicle Air Helicopter Attack",
        "Vehicle Main Battle Tanks", "Vehicle Anti Air"
    };

    public static SortedVehicleUnlocks sort(final Map<String, List<VehicleUnlock>> unlockMap, final SortMode mode) {
        if (mode == SortMode.PROGRESS) {
            return sortItemsByProgress(unlockMap);
        } else {
            return sortItemsByCategory(unlockMap);
        }
    }

    private static SortedVehicleUnlocks sortItemsByProgress(Map<String, List<VehicleUnlock>> unlockMap) {
        List<VehicleUnlock> list = new ArrayList<VehicleUnlock>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        Collections.sort(list);
        return new SortedVehicleUnlocks(list);
    }

    private static SortedVehicleUnlocks sortItemsByCategory(Map<String, List<VehicleUnlock>> unlockMap) {
        List<VehicleUnlock> list = new ArrayList<VehicleUnlock>();
        for (String key : CATEGORY_ORDER) {
            if (unlockMap.containsKey(key)) {
                list.addAll(unlockMap.get(key));
            }
        }
        return new SortedVehicleUnlocks(list);
    }
}
