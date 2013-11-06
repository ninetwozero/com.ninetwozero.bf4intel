package com.ninetwozero.bf4intel.assignments;

import com.google.gson.annotations.SerializedName;

public class Assignment {
    @SerializedName("completion")
    private int completion;
    @SerializedName("isTracking")
    private boolean isTracking;
    @SerializedName("award")
    private AssignmentAward award;

    public int getCompletion() {
        return completion;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public AssignmentAward getAward() {
        return award;
    }
}
