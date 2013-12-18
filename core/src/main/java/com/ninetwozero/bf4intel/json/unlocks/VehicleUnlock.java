package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class VehicleUnlock {
    @SerializedName("unlockId")
    private String name;
    @SerializedName("guid")
    private String guid;
    @SerializedName("unlockedBy")
    private VehicleUnlockCriteria criteria;

    public VehicleUnlock(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return guid;
    }

    public VehicleUnlockCriteria getCriteria() {
        return criteria;
    }
}
