package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class WeaponUnlock implements Comparable<WeaponUnlock> {
    @SerializedName("unlockId")
    private String name;
    @SerializedName("guid")
    private String guid;

    public WeaponUnlock(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return guid;
    }

    @Override
    public int compareTo(final WeaponUnlock otherUnlock) {
        return name.compareToIgnoreCase(otherUnlock.getName());
    }
}
