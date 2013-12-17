package com.ninetwozero.bf4intel.ui.stats.vehicles;

import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStats;

import java.util.List;

public class GroupedVehicleStats implements Comparable<GroupedVehicleStats> {

    private final int serviceStarsCount;
    private final int killCount;
    private final long timeInVehicle;
    private final List<VehicleStats> vehicleList;

    public GroupedVehicleStats(int serviceStarsCount, int killCount, long timeInVehicle, List<VehicleStats> vehicleList) {

        this.serviceStarsCount = serviceStarsCount;
        this.killCount = killCount;
        this.timeInVehicle = timeInVehicle;
        this.vehicleList = vehicleList;
    }

    public int getServiceStarsCount() {
        return serviceStarsCount;
    }

    public int getKillCount() {
        return killCount;
    }

    public long getTimeInVehicle() {
        return timeInVehicle;
    }

    public List<VehicleStats> getVehicleList() {
        return vehicleList;
    }

    @Override
    public int compareTo(GroupedVehicleStats other) {
        if (killCount == other.getKillCount()) {
            return timeInVehicle > other.getTimeInVehicle() ? -1 : timeInVehicle < other.getTimeInVehicle() ? +1 : 0;
        } else {
            return killCount > other.getKillCount() ? -1 : +1;
        }
    }
}
