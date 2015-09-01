package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class VehicleStatistics {

    @SerializedName("unlockProgressionAmount")
    private Map<String, VehicleServiceStars> unlockProgressions = new HashMap<String, VehicleServiceStars>();
    @SerializedName("mainVehicleStats")
    private List<VehicleStats> vehiclesList = new ArrayList<VehicleStats>();
    private static final List<String> vehicleGroupsList = new ArrayList<String>(){{
        {
            add("Vehicle Air Helicopter Attack");
            add("Vehicle Air Helicopter Scout");
            add("Vehicle Air Jet Attack");
            add("Vehicle Air Jet Stealth");
            add("Vehicle Anti Air");
            add("Vehicle Fast Attack Craft");
            add("Vehicle Infantry Fighting Vehicle");
            add("Vehicle Main Battle Tanks");
            add("Vehicle Transport");
            add("Weapon Stationary ");
            add("Vehicle Air");
            add("Vehicle Mobile Artillery");
            add("Soldier Equiment");
            add("Vehicle Boat");
        }
    }};

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
            if (vehicleGroupsList.contains(stat.getVehicleGroup())) {
                if (vehicleGroupsMap.containsKey(stat.getVehicleGroup())) {
                    group = vehicleGroupsMap.get(stat.getVehicleGroup());
                    group.addVehicleStats(stat);
                } else {
                    List<VehicleStats> vehicleStats = new ArrayList<VehicleStats>();
                    vehicleStats.add(stat);
                    group = new GroupedVehicleStats(
                        stat.getVehicleGroup(),
                        stat.getServiceStarsCount(),
                        stat.getServiceStarProgress(),
                        stat.getKillsCount(),
                        stat.getTimeInVehicle(),
                        vehicleStats
                    );
                    vehicleGroupsMap.put(stat.getVehicleGroup(), group);
                }
            }
        }
        List<GroupedVehicleStats> groupedVehicleStatsList = new ArrayList<GroupedVehicleStats>(vehicleGroupsMap.values());
        Collections.sort(groupedVehicleStatsList);

        for (GroupedVehicleStats stats : groupedVehicleStatsList) {
            Collections.sort(stats.getVehicleList());
        }

        return groupedVehicleStatsList;
    }
}
