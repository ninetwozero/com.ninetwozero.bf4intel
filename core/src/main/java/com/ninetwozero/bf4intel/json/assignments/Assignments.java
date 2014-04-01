package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignments {
    public static final int VERSION = 1;

    @SerializedName("allMissions")
    private Map<String, Assignment> assignments = new HashMap<String, Assignment>();
    @SerializedName("missionCategory")
    private Map<String, List<String>> assignmentCategory = new HashMap<String, List<String>>();

    public Map<String, Assignment> getAssignments() {
        return assignments;
    }

    public Map<String, List<String>> getAssignmentCategory() {
        return assignmentCategory;
    }
}
