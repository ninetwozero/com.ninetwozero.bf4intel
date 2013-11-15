package com.ninetwozero.bf4intel.assignments;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
    @SerializedName("completion")
    private int completion;
    @SerializedName("isTracking")
    private boolean isTracking;
    @SerializedName("award")
    private AssignmentAward award;
    @SerializedName("unlockDependencies")
    private List<UnlockDependencies> unlockDependencieses = new ArrayList<UnlockDependencies>();

    public int getCompletion() {
        return completion;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public AssignmentAward getAward() {
        return award;
    }

    public String getDependencyGroup() {
        return unlockDependencieses.isEmpty() ? AssignmentPrerequirement.NONE.toString() : unlockDependencieses.get(0).getGroup();
    }

    public class UnlockDependencies {
        @SerializedName("group")
        private String group;

        protected String getGroup() {
            return group;
        }
    }
}