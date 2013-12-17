package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

public class VehicleStats {
    @SerializedName("serviceStars")
    private int serviceStarsCount;
    @SerializedName("kills")
    private int killsCount;
    @SerializedName("timeIn")
    private int timeInVehicle;
    @SerializedName("guid")
    private String uniqueName;
    @SerializedName("category")
    private String vehicleGroup;

    public int getServiceStarsCount() {
        return serviceStarsCount;
    }

    public int getKillsCount() {
        return killsCount;
    }

    public int getTimeInVehicle() {
        return timeInVehicle;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getVehicleGroup() {
        return vehicleGroup;
    }
}
