package com.ninetwozero.bf4intel.json.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class SoldierListingRequest {
    @SerializedName("soldiersBox")
    private List<SummarizedSoldierStats> soldiers;

    @SerializedName("userGames")
    private Map<Integer, Integer[]> games;

    public List<SummarizedSoldierStats> getSoldiers() {
        return soldiers;
    }

    public Map<Integer, Integer[]> getGames() {
        return games;
    }
}
