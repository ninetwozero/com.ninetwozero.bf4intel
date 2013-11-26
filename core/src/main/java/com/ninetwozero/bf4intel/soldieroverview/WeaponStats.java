package com.ninetwozero.bf4intel.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class WeaponStats extends BaseStatsModel {
    @SerializedName("timeEquipped")
    private int timeEquipped;

    @SerializedName("shotsFired")
    private int shotsFired;

    @SerializedName("shotsHit")
    private int shotsHit;

    @SerializedName("accuracy")
    private double accuracy;

    @SerializedName("headshots")
    private int headshotCount;

    public int getTimeEquipped() {
        return timeEquipped;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getHeadshotCount() {
        return headshotCount;
    }
}
