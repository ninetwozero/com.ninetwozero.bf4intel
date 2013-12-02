package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MedalAward {
    @SerializedName("dependencies")
    private List<MedalDependency> medalDepencies = new ArrayList<MedalDependency>();

    public List<MedalDependency> getMedalDepencies() {
        return medalDepencies;
    }
}
