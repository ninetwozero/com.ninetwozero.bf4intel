package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class LeaderboardArea {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
