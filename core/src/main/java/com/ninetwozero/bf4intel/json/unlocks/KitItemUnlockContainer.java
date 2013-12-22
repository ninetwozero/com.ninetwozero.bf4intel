package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class KitItemUnlockContainer implements Comparable<KitItemUnlockContainer> {
    @SerializedName("kitItemUnlock")
    private WeaponUnlock kitItemUnlock;
    @SerializedName("weaponUnlock")
    private WeaponUnlock weaponUnlock;

    public WeaponUnlock getUnlock() {
        return weaponUnlock == null ? kitItemUnlock : weaponUnlock;
    }

    @Override
    public int compareTo(final KitItemUnlockContainer otherUnlock) {
        return getUnlock().compareTo(otherUnlock.getUnlock());
    }
}
