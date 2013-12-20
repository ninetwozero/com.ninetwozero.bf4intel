package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

public class VehicleStats implements Comparable<VehicleStats> {
    @SerializedName("serviceStars")
    private int serviceStarsCount;
    @SerializedName("serviceStarsProgress")
    private int serviceStarProgress;
    @SerializedName("kills")
    private int killsCount;
    @SerializedName("timeIn")
    private int timeInVehicle;
    @SerializedName("name")
    private String name;
    @SerializedName("category")
    private String vehicleGroup;

    public int getServiceStarsCount() {
        return serviceStarsCount;
    }

    public int getServiceStarProgress() {
        return serviceStarProgress;
    }

    public int getKillsCount() {
        return killsCount;
    }

    public int getTimeInVehicle() {
        return timeInVehicle;
    }

    public String getName() {
        return name;
    }

    public String getVehicleGroup() {
        return vehicleGroup;
    }

    @Override
    public int compareTo(VehicleStats other) {
        if (killsCount == other.getKillsCount()) {
            return timeInVehicle > other.getTimeInVehicle() ? -1 : timeInVehicle < other.getTimeInVehicle() ? +1 : 0;
        } else {
            return killsCount > other.getKillsCount() ? -1 : +1;
        }
    }
}
