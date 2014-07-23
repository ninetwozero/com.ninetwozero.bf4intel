package com.ninetwozero.bf4intel.json.stats.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedStatsContainer {
    @SerializedName("detailedStatsItem")
    private List<List<DetailedStatsItem>> items;

    public DetailedStatsContainer(List<List<DetailedStatsItem>> items) {
        this.items = items;
    }

    public List<List<DetailedStatsItem>> getItems() {
        return items;
    }
}
