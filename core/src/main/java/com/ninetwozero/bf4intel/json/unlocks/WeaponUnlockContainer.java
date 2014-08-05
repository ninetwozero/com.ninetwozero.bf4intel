package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class WeaponUnlockContainer implements Comparable<WeaponUnlockContainer>, UnlockContainer {
    @SerializedName("weaponUnlock")
    private WeaponUnlock unlock;
    @SerializedName("category")
    private String category;

    public WeaponUnlock getUnlock() {
        return unlock;
    }

    @Override
    public UnlockCriteria getCriteria() {
        return unlock.getCriteria();
    }

    @Override
    public int compareTo(final WeaponUnlockContainer otherUnlock) {
        return unlock.compareTo(otherUnlock.getUnlock());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

}
