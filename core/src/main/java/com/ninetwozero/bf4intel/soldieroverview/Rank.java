package com.ninetwozero.bf4intel.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class Rank {
    @SerializedName("name")
    private String name;

    @SerializedName("level")
    private int level;

    @SerializedName("pointsNeeded")
    private int pointsNeeded;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPointsNeeded() {
        return pointsNeeded;
    }

    public String getImageSlug() {
        return "r" + level;
    }
}
