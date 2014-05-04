package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentCriteriaContainer implements Serializable {
    @SerializedName("originalCriteria")
    private AssignmentCriteria criteria;
    @SerializedName("actualValue")
    private int currentValue;
    @SerializedName("completion")
    private int completion;

    public AssignmentCriteria getCriteria() {
        return criteria;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getCompletion() {
        return completion;
    }

    public boolean isCompleted() {
        return completion == 100;
    }
}
