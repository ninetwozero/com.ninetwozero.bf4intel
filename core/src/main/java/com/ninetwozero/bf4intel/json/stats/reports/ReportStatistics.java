package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReportStatistics {

    @SerializedName("gameReports")
    private List<StatsGameReport> statsGameReports = new ArrayList<StatsGameReport>();
    @SerializedName("favGameReports")
    private List<FavouriteGameReport> favoriteReports = new ArrayList<FavouriteGameReport>();
    @SerializedName("personaId")
    private int soldierId;

    public List<StatsGameReport> getStatsGameReports() {
        return statsGameReports;
    }

    public List<FavouriteGameReport> getFavoriteReports() {
        return favoriteReports;
    }

    public int getSoldierId() {
        return soldierId;
    }
}
