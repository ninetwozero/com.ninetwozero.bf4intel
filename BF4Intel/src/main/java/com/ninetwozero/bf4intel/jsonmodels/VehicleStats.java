package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class VehicleStats extends BaseStatsModel {
    @SerializedName("timeIn")
    private int mTimeIn;

    @SerializedName("destroyXinY")
    private int mDestroyedEnemySameVehicle;

    public int getTimeIn() {
        return mTimeIn;
    }

    public int getDestroyedEnemySameVehicle() {
        return mDestroyedEnemySameVehicle;
    }
}
