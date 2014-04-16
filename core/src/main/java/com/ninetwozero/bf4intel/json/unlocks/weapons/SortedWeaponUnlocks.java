package com.ninetwozero.bf4intel.json.unlocks.weapons;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;

import java.util.List;

public class SortedWeaponUnlocks {
    @SerializedName("sortedWeaponUnlocks")
    private List<WeaponUnlockContainer> sortedWeaponUnlocks;

    public SortedWeaponUnlocks(List<WeaponUnlockContainer> sortedWeaponUnlocks) {
        this.sortedWeaponUnlocks = sortedWeaponUnlocks;
    }

    public List<WeaponUnlockContainer> getSortedUnlocks() {
        return sortedWeaponUnlocks;
    }
}
