package com.ninetwozero.bf4intel.json.unlocks.vehicles;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;

import java.util.List;
import java.util.Map;

public class SortedVehicleUnlocks {
    @SerializedName("sortedVehicleUnlockMap")
    private Map<String, List<VehicleUnlock>> sortedVehicleUnlockMap;

    public SortedVehicleUnlocks(Map<String, List<VehicleUnlock>> sortedVehicleUnlockMap) {
        this.sortedVehicleUnlockMap = sortedVehicleUnlockMap;
    }

    public Map<String, List<VehicleUnlock>> getSortedVehicleUnlockMap() {
        return sortedVehicleUnlockMap;
    }
}
