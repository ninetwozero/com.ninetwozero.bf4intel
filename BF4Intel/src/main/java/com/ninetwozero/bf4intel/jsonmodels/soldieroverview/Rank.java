package com.ninetwozero.bf4intel.jsonmodels.soldieroverview;

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

    @Override
    public String toString() {
        return "Rank{" +
            "name='" + name + '\'' +
            ", level=" + level +
            ", pointsNeeded=" + pointsNeeded +
            '}';
    }
}
