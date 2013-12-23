package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

import java.util.*;

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

    public List<GroupedVehicleStats> fetchGroupVehicles() {
        List<VehicleStats> loaderVehicleList = getVehiclesList();
        Map<String, GroupedVehicleStats> vehicleGroupsMap = new HashMap<String, GroupedVehicleStats>();
        GroupedVehicleStats group;
        for (VehicleStats stat : loaderVehicleList) {
            if (vehicleGroupsMap.containsKey(stat.getVehicleGroup())) {
                GroupedVehicleStats temp = vehicleGroupsMap.get(stat.getVehicleGroup());
                List<VehicleStats> vehicleStats = new ArrayList<VehicleStats>(temp.getVehicleList());
                vehicleStats.add(stat);
                group = new GroupedVehicleStats(temp.getGroupName(),
                    temp.getServiceStarsCount(),
                    temp.getServiceStarProgress(),
                    temp.getKillCount() + stat.getKillsCount(),
                    temp.getTimeInVehicle() + stat.getTimeInVehicle(),
                    vehicleStats);

            } else {
                List<VehicleStats> vehicleStats = new ArrayList<VehicleStats>();
                vehicleStats.add(stat);
                group = new GroupedVehicleStats(
                    stat.getVehicleGroup(),
                    stat.getServiceStarsCount(),
                    stat.getServiceStarProgress(),
                    stat.getKillsCount(),
                    stat.getTimeInVehicle(),
                    vehicleStats);
            }
            vehicleGroupsMap.put(stat.getVehicleGroup(), group);
        }
        List<GroupedVehicleStats> groupedVehicleStatsList = new ArrayList<GroupedVehicleStats>(vehicleGroupsMap.values());
        Collections.sort(groupedVehicleStatsList);
        return groupedVehicleStatsList;
    }
}
