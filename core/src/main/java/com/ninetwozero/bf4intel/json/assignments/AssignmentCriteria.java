package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentCriteria implements Serializable {
    private static final int COMPLETED_THRESHOLD = 100;

    @SerializedName("descriptionID")
    private String key;
    @SerializedName("completionValue")
    private int unlockThreshold;
    @SerializedName("actualValue")
    private int currentValue;
    @SerializedName("completion")
    private int completion;
    @SerializedName("criteriaType")
    private String criteriaType;

    public AssignmentCriteria(String key, int currentValue, int unlockThreshold, int completion, String criteriaType) {
        this.key = key;
        this.currentValue = currentValue;
        this.unlockThreshold = unlockThreshold;
        this.completion = completion;
        this.criteriaType = criteriaType;
    }

    public String getKey() {
        return key;
    }

    public int getUnlockThreshold() {
        return unlockThreshold;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getCompletion() {
        return completion;
    }

    public boolean isCompleted() {
        return completion == COMPLETED_THRESHOLD;
    }

    public String getCriteriaType() {
        return criteriaType;
    }
}
