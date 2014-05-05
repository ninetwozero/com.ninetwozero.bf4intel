package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentAward implements Serializable {

    @SerializedName("stringID")
    private String uniqueName;
    @SerializedName("code")
    private String code;
    @SerializedName("license")
    private String expansionPack;

    public String getUniqueName() {
        return uniqueName;
    }

    public String getCode() {
        return code;
    }

    public String getExpansionPack() {
        return expansionPack;
    }

    public boolean hasExpansionPack() {
        return expansionPack != null;
    }
}
