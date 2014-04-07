package com.ninetwozero.bf4intel.json.unlocks.kits;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;

import java.util.List;
import java.util.Map;

public class SortedKitUnlocks {
    @SerializedName("sortedKitUnlockMap")
    private Map<String, List<KitItemUnlockContainer>> sortedKitUnlockMap;

    public SortedKitUnlocks(Map<String, List<KitItemUnlockContainer>> sortedKitUnlockMap) {
        this.sortedKitUnlockMap = sortedKitUnlockMap;
    }

    public Map<String, List<KitItemUnlockContainer>> getSortedKitUnlockMap() {
        return sortedKitUnlockMap;
    }
}
