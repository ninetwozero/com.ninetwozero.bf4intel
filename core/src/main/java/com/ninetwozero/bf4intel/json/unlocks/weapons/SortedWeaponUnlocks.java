package com.ninetwozero.bf4intel.json.unlocks.weapons;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;

import java.util.List;
import java.util.Map;

public class SortedWeaponUnlocks {
    @SerializedName("sortedWeaponUnlocksMap")
    private Map<String, List<WeaponUnlockContainer>> sortedWeaponUnlocksMap;

    public SortedWeaponUnlocks(Map<String, List<WeaponUnlockContainer>> sortedWeaponUnlocksMap) {
        this.sortedWeaponUnlocksMap = sortedWeaponUnlocksMap;
    }

    public Map<String, List<WeaponUnlockContainer>> getSortedWeaponUnlocksMap() {
        return sortedWeaponUnlocksMap;
    }
}
