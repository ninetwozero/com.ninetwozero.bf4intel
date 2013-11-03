package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class VehicleStats extends BaseStatsModel {
    @SerializedName("timeIn")
    private int mTimeIn;

    @SerializedName("destroyXinY")
    private int mDestroyedEnemySameVehicle;

    public VehicleStats(
        final String name, final String slug, final String guid, final String category, final String code,
        final int serviceStars, final int serviceStarProgress, final int timeIn,
        final int destroyedEnemySameVehicle, final int killCount
    ) {
        super(name, slug, guid, category, code, serviceStars, serviceStarProgress, killCount);

        mTimeIn = timeIn;
        mDestroyedEnemySameVehicle = destroyedEnemySameVehicle;
    }

    public int getTimeIn() {
        return mTimeIn;
    }

    public int getDestroyedEnemySameVehicle() {
        return mDestroyedEnemySameVehicle;
    }
}
