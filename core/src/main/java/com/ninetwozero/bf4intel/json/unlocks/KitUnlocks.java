package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class KitUnlocks {
    @SerializedName("unlocks")
    private Map<String, List<KitItemUnlockContainer>> unlockMap;

    public KitUnlocks(Map<String, List<KitItemUnlockContainer>> unlockMap) {
        this.unlockMap = unlockMap;
    }

    public Map<String, List<KitItemUnlockContainer>> getUnlockMap() {
        return unlockMap;
    }
}
