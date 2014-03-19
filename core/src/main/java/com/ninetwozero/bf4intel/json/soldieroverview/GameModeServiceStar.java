package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class GameModeServiceStar {
    @SerializedName("codeNeeded")
    private String criteria;
    @SerializedName("serviceStars")
    private int serviceStarCount;
    @SerializedName("serviceStarsProgress")
    private int serviceStarProgress;
    @SerializedName("actualValue")
    private int currentScore;
    @SerializedName("valueNeeded")
    private int nextStarThreshold;

    public String getCriteria() {
        return criteria;
    }

    public int getServiceStarCount() {
        return serviceStarCount;
    }

    public int getServiceStarProgress() {
        return serviceStarProgress;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getNextStarThreshold() {
        return nextStarThreshold;
    }
}
