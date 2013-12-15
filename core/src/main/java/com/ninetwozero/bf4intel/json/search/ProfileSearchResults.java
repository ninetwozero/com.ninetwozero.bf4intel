package com.ninetwozero.bf4intel.json.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileSearchResults {
    @SerializedName("data")
    private List<ProfileSearchResult> results;

    public List<ProfileSearchResult> getResults() {
        return results;
    }
}
