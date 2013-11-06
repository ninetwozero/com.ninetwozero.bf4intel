package com.ninetwozero.bf4intel.assignments;

import com.google.gson.annotations.SerializedName;

public class AssignmentAward {

    @SerializedName("code")
    private String assignmentKey;
    @SerializedName("awardGroup")
    private String awardGroup;

    public String getAssignmentKey() {
        return assignmentKey;
    }

    public String getAwardGroup() {
        return awardGroup;
    }
}
