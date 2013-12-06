package com.ninetwozero.bf4intel.json.weaponstats;

import com.google.gson.annotations.SerializedName;

public class Weapon {

    @SerializedName("serviceStars")
    private int serviceStarsCount;
    @SerializedName("serviceStarsProgress")
    private int serviceStarsProgress;
    @SerializedName("categorySID")
    private String categorySID;
    @SerializedName("category")
    private String category;
    @SerializedName("kills")
    private int kills;
    @SerializedName("slug")
    private String name;
    @SerializedName("timeEquipped")
    private long timeEquipped;
    @SerializedName("name")
    private String uniqueName;

    public int getServiceStarsCount() {
        return serviceStarsCount;
    }

    public int getServiceStarsProgress() {
        return serviceStarsProgress;
    }

    public String getCategorySID() {
        return categorySID;
    }

    public String getCategory() {
        return category;
    }

    public int getKills() {
        return kills;
    }

    public String getName() {
        return name;
    }

    public long getTimeEquipped() {
        return timeEquipped;
    }

    public String getUniqueName() {
        return uniqueName;
    }
}
