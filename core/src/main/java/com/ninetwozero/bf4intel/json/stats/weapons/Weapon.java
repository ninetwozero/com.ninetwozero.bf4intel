package com.ninetwozero.bf4intel.json.stats.weapons;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weapon implements Comparable<Weapon>, Serializable {
    @SerializedName("name")
    private String uniqueName;
    @SerializedName("slug")
    private String slug;
    @SerializedName("categorySID")
    private String categorySID;
    @SerializedName("category")
    private String category;
    @SerializedName("kills")
    private int kills;
    @SerializedName("serviceStars")
    private int serviceStarsCount;
    @SerializedName("serviceStarsProgress")
    private int serviceStarsProgress;
    @SerializedName("timeEquipped")
    private long timeEquipped;
    @SerializedName("shotsFired")
    private int shotsFired;
    @SerializedName("shotsHit")
    private int shotsHit;
    @SerializedName("accuracy")
    private double accuracy;
    @SerializedName("headshots")
    private int headshotCount;

    public String getUniqueName() {
        return uniqueName;
    }

    public String getSlug() {
        return slug;
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

    public int getServiceStarsCount() {
        return serviceStarsCount;
    }

    public int getServiceStarsProgress() {
        return serviceStarsProgress;
    }

    public long getTimeEquipped() {
        return timeEquipped;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getHeadshotCount() {
        return headshotCount;
    }

    public int compareTo(Weapon w) {
        if (kills == w.kills) {
            return timeEquipped > w.timeEquipped ? -1 : timeEquipped < w.timeEquipped ? +1 : 0;
        } else {
            return kills > w.kills ? -1 : +1;
        }
    }
}
