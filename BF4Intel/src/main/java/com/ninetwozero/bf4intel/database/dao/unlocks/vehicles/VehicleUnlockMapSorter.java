package com.ninetwozero.bf4intel.database.dao.unlocks.vehicles;

import com.ninetwozero.bf4intel.database.dao.unlocks.UnlockSorter;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import java.util.List;
import java.util.Map;

public class VehicleUnlockMapSorter extends UnlockSorter<SortedVehicleUnlocks, VehicleUnlock> {
    public static final String[] CATEGORY_ORDER = new String[] {
        "Vehicle Air Helicopter Scout", "Vehicle Air Jet Attack",
        "Vehicle Fast Attack Craft", "Vehicle Infantry Fighting Vehicle",
        "Vehicle Air Jet Stealth", "Vehicle Air Helicopter Attack",
        "Vehicle Main Battle Tanks", "Vehicle Anti Air"
    };

    public VehicleUnlockMapSorter(final Map<String, List<VehicleUnlock>> unlockMap) {
        super(unlockMap);
    }

    @Override
    protected void addCategoryToItems(final List<VehicleUnlock> unlocks, final String category) {
        for (VehicleUnlock unlock : unlocks) {
            unlock.setCategory(category);
        }
    }

    @Override
    protected SortedVehicleUnlocks createNewObject(final List<VehicleUnlock> list) {
        return new SortedVehicleUnlocks(list);
    }

    @Override
    protected String[] getCategoryOrder() {
        return CATEGORY_ORDER;
    }
}
