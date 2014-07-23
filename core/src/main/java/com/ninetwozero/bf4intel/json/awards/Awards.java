package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Awards {

    @SerializedName("groupedMedalAwards")
    private Map<String, List<String>> awardsGroups = new HashMap<String, List<String>>();
    @SerializedName("ribbonAwardByCode")
    private Map<String, Ribbon> ribbons = new HashMap<String, Ribbon>();
    @SerializedName("medalAwardByCode")
    private Map<String, Medal> medals = new HashMap<String, Medal>();

    public Map<String, List<String>> getAwardsGroups() {
        return awardsGroups;
    }

    public Map<String, Ribbon> getRibbons() {
        return ribbons;
    }

    public Map<String, Medal> getMedals() {
        return medals;
    }
}
