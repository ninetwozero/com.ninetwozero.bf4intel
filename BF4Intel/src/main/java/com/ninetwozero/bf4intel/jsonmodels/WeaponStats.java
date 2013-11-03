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

    public WeaponStats(
        final String name, final String slug, final String guid, final String code, final String category,
        final int serviceStars, final int serviceStarProgress, final int timeEquipped,
        final int shotsFired, final int shotsHit, final double accuracy, final int headshotCount, final int killCount
    ) {
        super(name, slug, guid, category, code, serviceStars, serviceStarProgress, killCount);

        mTimeEquipped = timeEquipped;
        mShotsFired = shotsFired;
        mShotsHit = shotsHit;
        mAccuracy = accuracy;
        mHeadshotCount = headshotCount;
    }

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
