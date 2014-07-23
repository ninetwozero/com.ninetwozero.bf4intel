package com.ninetwozero.bf4intel.json.stats.weapons;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

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
    @SerializedName("code")
    private String groupCode;

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
        return Comparators.KILLS.compare(this, w);
    }

    public String getGroupCode() {
        return groupCode;
    }

    public static class Comparators {
        public static Comparator<Weapon> KILLS = new Comparator<Weapon>() {
            @Override
            public int compare(Weapon w1, Weapon w2) {
                if (w1.kills == w2.kills) {
                    return w1.timeEquipped > w2.timeEquipped ? -1 : w1.timeEquipped < w2.timeEquipped ? +1 : 0;
                } else {
                    return w1.kills > w2.kills ? -1 : +1;
                }
            }
        };
        public static Comparator<Weapon> PROGRESS = new Comparator<Weapon>() {
            @Override
            public int compare(Weapon w1, Weapon w2) {
                if ( w1.serviceStarsProgress > w2.getServiceStarsProgress()) {
                    return -1;
                } else if (w1.serviceStarsProgress < w2.getServiceStarsProgress()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }
}
