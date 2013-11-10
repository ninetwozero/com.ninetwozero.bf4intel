package com.ninetwozero.bf4intel.assignments;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignments {

    @SerializedName("personaId")
    private long personaId;
    @SerializedName("personaName")
    private String personaName;
    @SerializedName("allMissions")
    private Map<String, Assignment> assignments = new HashMap<String, Assignment>();
    @SerializedName("missionCategory")
    private Map<String, List<String>> assignmentCategory = new HashMap<String, List<String>>();


    public long getPersonaId() {
        return personaId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public Map<String, Assignment> getAssignments() {
        return assignments;
    }

    public Map<String, List<String>> getAssignmentCategory() {
        return assignmentCategory;
    }
}
