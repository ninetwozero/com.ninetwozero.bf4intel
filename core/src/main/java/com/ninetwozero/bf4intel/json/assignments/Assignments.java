package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignments {
    @SerializedName("allMissions")
    private Map<String, Assignment> assignments = new HashMap<String, Assignment>();
    @SerializedName("missionCategory")
    private Map<String, List<String>> assignmentCategory = new HashMap<String, List<String>>();
    @SerializedName("userGameExpansions")
    private Map<String, List<Long>> expansions = new HashMap<String, List<Long>>();

    public Map<String, Assignment> getAssignments() {
        return assignments;
    }

    public Map<String, List<String>> getAssignmentCategory() {
        return assignmentCategory;
    }

    public Map<String, List<Long>> getExpansions() {
        return expansions;
    }
}
