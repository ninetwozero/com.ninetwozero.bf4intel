package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AssignmentAward implements Serializable {

    @SerializedName("stringID")
    private String uniqueName;
    @SerializedName("code")
    private String code;
    @SerializedName("license")
    private String expansionPack;

    @SerializedName("criterias")
    private List<AwardCriterias> awardCriterias;

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

    public List<AwardCriterias> getAwardCriterias() {
        return awardCriterias;
    }
}
