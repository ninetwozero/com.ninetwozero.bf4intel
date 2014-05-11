package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortedAssignmentContainer {
    @SerializedName("sortedAssignmentList")
    private final List<Assignment> items;
    @SerializedName("playerExpansions")
    private final Map<String, List<Long>> expansions;

    public SortedAssignmentContainer() {
        this.items = new ArrayList<Assignment>();
        this.expansions = new HashMap<String, List<Long>>();
    }

    public SortedAssignmentContainer(List<Assignment> items, Map<String, List<Long>> expansions) {
        this.items = items;
        this.expansions = expansions;
    }

    public List<Assignment> getItems() {
        return items;
    }

    public Map<String, List<Long>> getExpansions() {
        return expansions;
    }
}
