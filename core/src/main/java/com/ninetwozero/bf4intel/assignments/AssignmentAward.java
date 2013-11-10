package com.ninetwozero.bf4intel.assignments;

import com.google.gson.annotations.SerializedName;

public class AssignmentAward {

    @SerializedName("code")
    private String assignmentKey;

    public String getAssignmentKey() {
        return assignmentKey;
    }

}
