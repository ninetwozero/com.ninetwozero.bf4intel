package com.ninetwozero.bf4intel.json.unlocks.vehicles;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlock;

import java.util.List;

public class SortedVehicleUnlocks {
    @SerializedName("sortedVehicleUnlocks")
    private List<VehicleUnlock> sortedVehicleUnlocks;

    public SortedVehicleUnlocks(List<VehicleUnlock> sortedVehicleUnlocks) {
        this.sortedVehicleUnlocks = sortedVehicleUnlocks;
    }

    public List<VehicleUnlock> getSortedVehicles() {
        return sortedVehicleUnlocks;
    }
}
