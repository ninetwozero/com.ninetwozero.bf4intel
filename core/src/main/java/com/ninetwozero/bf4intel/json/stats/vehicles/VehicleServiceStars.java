package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

public class VehicleServiceStars {

    @SerializedName("taken")
    private int starsTaken;
    @SerializedName("total")
    private int starsTotal;

    public int getStarsTaken() {
        return starsTaken;
    }

    public int getStarsTotal() {
        return starsTotal;
    }
}
