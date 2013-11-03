package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public abstract class BaseStatsModel {
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

    public BaseStatsModel(
            final String name, final String slug, final String guid, final String category,
            final String code, final int serviceStars, final int serviceStarProgress, final int killCount
    ) {
        mName = name;
        mSlug = slug;
        mGuid = guid;
        mCategory = category;
        mCode = code;
        mServiceStars = serviceStars;
        mServiceStarProgress = serviceStarProgress;
        mKillCount = killCount;
    }

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
