package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleStatistics {

    @SerializedName("unlockProgressionAmount")
    private Map<String, VehicleServiceStars> unlockProgressions = new HashMap<String, VehicleServiceStars>();
    @SerializedName("mainVehicleStats")
    private List<VehicleStats> vehiclesList = new ArrayList<VehicleStats>();

    public Map<String, VehicleServiceStars> getUnlockProgressions() {
        return unlockProgressions;
    }

    public List<VehicleStats> getVehiclesList() {
        return vehiclesList;
    }
}
