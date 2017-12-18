package com.ninetwozero.bf4intel.json.assignments;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AwardCriterias implements Serializable {
    @SerializedName("completionValue")
    private int completionValue;

    public int getCompletionValue() {
        return completionValue;
    }
}
