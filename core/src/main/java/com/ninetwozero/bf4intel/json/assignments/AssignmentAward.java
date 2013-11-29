package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

public class AssignmentAward {

    @SerializedName("code")
    private String assignmentKey;
    @SerializedName("licence")
    private String expansionPack;

    public String getAssignmentKey() {
        return assignmentKey;
    }

    public String getExpansionPack() {
        return expansionPack;
    }
}
