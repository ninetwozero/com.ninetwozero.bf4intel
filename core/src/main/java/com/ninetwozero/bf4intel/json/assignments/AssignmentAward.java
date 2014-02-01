package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

public class AssignmentAward {

    @SerializedName("code")
    private String assignmentKey;
    @SerializedName("stringID")
    private String uniqueName;
    @SerializedName("license")
    private String expansionPack;

    public String getAssignmentKey() {
        return assignmentKey;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getExpansionPack() {
        return expansionPack;
    }

    public boolean hasExpansionPack() {
        return expansionPack != null;
    }
}
