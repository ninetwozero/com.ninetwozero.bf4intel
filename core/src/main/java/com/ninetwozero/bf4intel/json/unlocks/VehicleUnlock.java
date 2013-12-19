package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class VehicleUnlock implements Comparable<VehicleUnlock> {
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

    @Override
    public int compareTo(VehicleUnlock otherVehicle) {
        final int completion1 = criteria.getCompletion();
        final int completion2 = otherVehicle.getCriteria().getCompletion();

        if ((completion1 < 100 && completion2 < 100) ) {
            if (completion1 > completion2) {
                return -1;
            } else if (completion1 < completion2) {
                return 1;
            }
        } else if(completion1 == completion2) {
            return name.compareToIgnoreCase(otherVehicle.getName());
        } else {
            if (completion1 == 100) {
                return 1;
            } else if (completion2 == 100) {
                return -1;
            }
        }
        return name.compareToIgnoreCase(otherVehicle.getName());
    }
}
