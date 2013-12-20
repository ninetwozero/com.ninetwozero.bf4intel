package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class KitUnlocks {
    @SerializedName("unlockProgression")
    private Map<String, List<KitUnlock>> unlockMap;

    public Map<String, List<KitUnlock>> getUnlockMap() {
        return unlockMap;
    }
}
