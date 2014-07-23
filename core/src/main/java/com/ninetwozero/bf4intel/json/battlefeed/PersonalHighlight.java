package com.ninetwozero.bf4intel.json.battlefeed;

import com.google.gson.annotations.SerializedName;

public class PersonalHighlight {
    @SerializedName("type")
    private String type;

    @SerializedName("score")
    private int score;

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }
}
