package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class BaseStatsModel {
    @SerializedName("name")
    protected String mName;

    @SerializedName("slug")
    protected String mSlug;

    @SerializedName("guid")
    protected String mGuid;

    @SerializedName("category")
    protected String mCategory;

    @SerializedName("code")
    protected String mCode;

    @SerializedName("serviceStars")
    protected int mServiceStars;

    @SerializedName("serviceStarsProgress")
    protected int mServiceStarProgress;

    @SerializedName("kills")
    protected int mKillCount;

    public String getName() {
        return mName;
    }

    public String getSlug() {
        return mSlug;
    }

    public String getGuid() {
        return mGuid;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getCode() {
        return mCode;
    }

    public int getServiceStars() {
        return mServiceStars;
    }

    public int getServiceStarProgress() {
        return mServiceStarProgress;
    }

    public int getKillCount() {
        return mKillCount;
    }
}
