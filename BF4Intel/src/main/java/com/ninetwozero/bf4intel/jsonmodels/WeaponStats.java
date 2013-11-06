package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class WeaponStats extends BaseStatsModel {
    @SerializedName("timeEquipped")
    private int mTimeEquipped;

    @SerializedName("shotsFired")
    private int mShotsFired;

    @SerializedName("shotsHit")
    private int mShotsHit;

    @SerializedName("accuracy")
    private double mAccuracy;

    @SerializedName("headshots")
    private int mHeadshotCount;

    public int getTimeEquipped() {
        return mTimeEquipped;
    }

    public int getShotsFired() {
        return mShotsFired;
    }

    public int getShotsHit() {
        return mShotsHit;
    }

    public double getAccuracy() {
        return mAccuracy;
    }

    public int getHeadshotCount() {
        return mHeadshotCount;
    }
}
