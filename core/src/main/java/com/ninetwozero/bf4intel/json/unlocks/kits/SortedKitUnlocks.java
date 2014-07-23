package com.ninetwozero.bf4intel.json.unlocks.kits;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;

import java.util.List;

public class SortedKitUnlocks {
    @SerializedName("sortedKitUnlocks")
    private List<KitItemUnlockContainer> sortedKitUnlocks;

    public SortedKitUnlocks(List<KitItemUnlockContainer> sortedKitUnlocks) {
        this.sortedKitUnlocks = sortedKitUnlocks;
    }

    public List<KitItemUnlockContainer> getSortedUnlocks() {
        return sortedKitUnlocks;
    }
}
