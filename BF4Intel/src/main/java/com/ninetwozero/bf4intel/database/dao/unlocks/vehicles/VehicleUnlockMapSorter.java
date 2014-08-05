package com.ninetwozero.bf4intel.database.dao.unlocks.vehicles;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VehicleUnlockMapSorter extends AbstractSorter<SortedVehicleUnlocks> {
    public static final String[] CATEGORY_ORDER = new String[] {
        "Vehicle Air Helicopter Scout", "Vehicle Air Jet Attack",
        "Vehicle Fast Attack Craft", "Vehicle Infantry Fighting Vehicle",
        "Vehicle Air Jet Stealth", "Vehicle Air Helicopter Attack",
        "Vehicle Main Battle Tanks", "Vehicle Anti Air"
    };
    private final Map<String, List<VehicleUnlock>> unlockMap;

    public VehicleUnlockMapSorter(final Map<String, List<VehicleUnlock>> unlockMap) {
        this.unlockMap = unlockMap;
    }

    @Override
    protected SortedVehicleUnlocks sortByProgress() {
        List<VehicleUnlock> list = new ArrayList<VehicleUnlock>();
        for (String key : unlockMap.keySet()) {
            final List<VehicleUnlock> allUnlocks = unlockMap.get(key);
            addCategoryToItems(allUnlocks, key);
            list.addAll(allUnlocks);
        }
        Collections.sort(list);
        return new SortedVehicleUnlocks(list);
    }

    @Override
    protected SortedVehicleUnlocks sortByCategory() {
        List<VehicleUnlock> list = new ArrayList<VehicleUnlock>();
        for (String key : CATEGORY_ORDER) {
            if (unlockMap.containsKey(key)) {
                final List<VehicleUnlock> allUnlocks = unlockMap.get(key);
                addCategoryToItems(allUnlocks, key);
                list.addAll(allUnlocks);
            }
        }
        return new SortedVehicleUnlocks(list);
    }

    private void addCategoryToItems(final List<VehicleUnlock> unlocks, final String category) {
        for (VehicleUnlock unlock : unlocks) {
            unlock.setCategory(category);
        }
    }
}
