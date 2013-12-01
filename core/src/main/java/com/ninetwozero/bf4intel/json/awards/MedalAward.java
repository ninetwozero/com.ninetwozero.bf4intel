package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MedalAward {
    @SerializedName("dependencies")
    private List<MedalDepency> medalDepencies = new ArrayList<MedalDepency>();

    public List<MedalDepency> getMedalDepencies() {
        return medalDepencies;
    }
}
