package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GroupedVehicleStatsContainer {
    @SerializedName("groupedVehicleStatistics")
    private final List<GroupedVehicleStats> items;

    public GroupedVehicleStatsContainer() {
        this.items = new ArrayList<GroupedVehicleStats>();
    }

    public GroupedVehicleStatsContainer(List<GroupedVehicleStats> items) {
        this.items = items;
    }

    public List<GroupedVehicleStats> getItems() {
        return items;
    }
}
