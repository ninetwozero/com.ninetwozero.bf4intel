package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class VehicleUnlocks {
    @SerializedName("unlockProgression")
    private Map<String, List<VehicleUnlock>> unlockMap;

    private Map<String, List<VehicleUnlock>> getUnlockMap() {
        return unlockMap;
    }
}
