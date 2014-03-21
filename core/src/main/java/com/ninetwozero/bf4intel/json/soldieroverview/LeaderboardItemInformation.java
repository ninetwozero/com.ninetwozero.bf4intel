package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class LeaderboardItemInformation {
    @SerializedName("nameSID")
    private String name;

    @SerializedName("statKey")
    private String key;

    @SerializedName("slug")
    private String slug;

    @SerializedName("category")
    private String category;

    @SerializedName("image")
    private String image;

    @SerializedName("statType")
    private String statType;

    @SerializedName("scoreType")
    private String scoreType;

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getSlug() {
        return slug;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getStatType() {
        return statType;
    }

    public String getScoreType() {
        return scoreType;
    }
}
