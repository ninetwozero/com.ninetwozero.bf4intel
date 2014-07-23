package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SortedAwardContainer {
    @SerializedName("sortedAwardList")
    private final List<Award> items;

    public SortedAwardContainer() {
        this.items = new ArrayList<Award>();
    }

    public SortedAwardContainer(List<Award> items) {
        this.items = items;
    }

    public List<Award> getItems() {
        return items;
    }
}
