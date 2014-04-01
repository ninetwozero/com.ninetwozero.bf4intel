package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SortedAssignmentContainer {
    @SerializedName("sortedAssignmentList")
    private final List<Assignment> items;

    public SortedAssignmentContainer() {
        this.items = new ArrayList<Assignment>();
    }

    public SortedAssignmentContainer(List<Assignment> items) {
        this.items = items;
    }

    public List<Assignment> getItems() {
        return items;
    }
}
