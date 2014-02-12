package com.ninetwozero.bf4intel.json.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class SoldierListingRequest {
    @SerializedName("soldiersBox")
    private List<SummarizedSoldierStats> soldiers;

    @SerializedName("userGameExpansions")
    private Map<Integer, Integer[]> gameExpansions;

    @SerializedName("userGames")
    private Map<Integer, Integer[]> games;

    public List<SummarizedSoldierStats> getSoldiers() {
        return soldiers;
    }

    public Map<Integer, Integer[]> getGameExpansions() {
        return gameExpansions;
    }

    public Map<Integer, Integer[]> getGames() {
        return games;
    }
}
