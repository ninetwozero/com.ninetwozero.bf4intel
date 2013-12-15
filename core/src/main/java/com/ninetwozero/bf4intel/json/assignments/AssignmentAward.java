package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

public class AssignmentAward {

    @SerializedName("code")
    private String assignmentKey;
    @SerializedName("stringID")
    private String stringID;
    @SerializedName("license")
    private String expansionPack;

    public String getAssignmentKey() {
        return assignmentKey;
    }

    public String getStringID() {
        return stringID;
    }

    public String getExpansionPack() {
        return expansionPack != null ? expansionPack : "";
    }
}
