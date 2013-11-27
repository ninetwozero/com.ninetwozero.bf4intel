package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class VehicleStats extends BaseStatsModel {
    @SerializedName("timeIn")
    private int timeIn;

    @SerializedName("destroyXinY")
    private int destroyedEnemySameVehicle;

    public int getTimeIn() {
        return timeIn;
    }

    public int getDestroyedEnemySameVehicle() {
        return destroyedEnemySameVehicle;
    }
}
