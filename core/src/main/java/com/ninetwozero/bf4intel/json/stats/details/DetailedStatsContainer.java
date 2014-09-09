package com.ninetwozero.bf4intel.json.stats.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedStatsContainer {
    @SerializedName("detailedStatsGroups")
    private List<DetailedStatsGroup> groups;

    public DetailedStatsContainer(List<DetailedStatsGroup> groups) {
        this.groups = groups;
    }

    public List<DetailedStatsGroup> getGroups() {
        return groups;
    }
}
