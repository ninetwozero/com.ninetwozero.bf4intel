package com.ninetwozero.bf4intel.jsonmodels;

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

    @Override
    public String toString() {
        return "VehicleStats{" +
            "timeIn=" + timeIn +
            ", destroyedEnemySameVehicle=" + destroyedEnemySameVehicle +
            '}';
    }
}
