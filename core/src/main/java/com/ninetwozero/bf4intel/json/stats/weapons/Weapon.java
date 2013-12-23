package com.ninetwozero.bf4intel.json.stats.weapons;

import com.google.gson.annotations.SerializedName;

public class Weapon implements Comparable<Weapon> {

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

    public int compareTo(Weapon w) {
        if(kills == w.kills) {
            return timeEquipped > w.timeEquipped ? -1 : timeEquipped < w.timeEquipped ? +1 : 0;
        } else {
            return kills > w.kills ? -1 : +1;
        }
    }
}
