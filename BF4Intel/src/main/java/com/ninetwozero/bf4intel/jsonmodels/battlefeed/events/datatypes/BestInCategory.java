package com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.datatypes;

import com.google.gson.annotations.SerializedName;

public class BestInCategory {
    @SerializedName("guid")
    private String id;

    @SerializedName("nameSID")
    private String name;

    @SerializedName("kills")
    private int killCount;

    @SerializedName("slug")
    private String slug;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getKillCount() {
        return killCount;
    }

    public String getSlug() {
        return slug;
    }
}
