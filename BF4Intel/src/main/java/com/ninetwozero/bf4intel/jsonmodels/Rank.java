package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class Rank {
    @SerializedName("name")
    private String mName;

    @SerializedName("level")
    private int mLevel;

    @SerializedName("pointsNeeded")
    private int mPointsNeeded;

    public Rank(final String name, final int level, final int pointsNeeded) {
        mName = name;
        mLevel = level;
        mPointsNeeded = pointsNeeded;
    }

    public String getName() {
        return mName;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getPointsNeeded() {
        return mPointsNeeded;
    }

    public String getImageSlug() {
        return "r" + mLevel;
    }
}
