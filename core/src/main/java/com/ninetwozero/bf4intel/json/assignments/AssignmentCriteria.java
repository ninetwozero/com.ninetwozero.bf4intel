package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentCriteria implements Serializable {
    @SerializedName("descriptionID")
    private String key;
    @SerializedName("completionValue")
    private int unlockThreshold;

    public String getKey() {
        return key;
    }

    public int getUnlockThreshold() {
        return unlockThreshold;
    }
}
