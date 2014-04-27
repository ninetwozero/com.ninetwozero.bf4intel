package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MedalDependency implements Serializable {
    @SerializedName("count")
    private int count;
    @SerializedName("code")
    private String ribbonDependency;

    public int getCount() {
        return count;
    }

    public String getRibbonDependency() {
        return ribbonDependency;
    }
}
