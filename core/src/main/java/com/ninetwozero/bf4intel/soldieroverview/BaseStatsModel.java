package com.ninetwozero.bf4intel.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class BaseStatsModel {
    @SerializedName("name")
    protected String name;

    @SerializedName("slug")
    protected String slug;

    @SerializedName("guid")
    protected String guid;

    @SerializedName("category")
    protected String category;

    @SerializedName("code")
    protected String code;

    @SerializedName("serviceStars")
    protected int serviceStars;

    @SerializedName("serviceStarsProgress")
    protected int serviceStarProgress;

    @SerializedName("kills")
    protected int killCount;

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getGuid() {
        return guid;
    }

    public String getCategory() {
        return category;
    }

    public String getCode() {
        return code;
    }

    public int getServiceStars() {
        return serviceStars;
    }

    public int getServiceStarProgress() {
        return serviceStarProgress;
    }

    public int getKillCount() {
        return killCount;
    }
}
