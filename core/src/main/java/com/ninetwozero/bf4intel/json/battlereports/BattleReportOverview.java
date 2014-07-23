package com.ninetwozero.bf4intel.json.battlereports;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BattleReportOverview {
    @SerializedName("personaId")
    private String personaId;
    @SerializedName("platform")
    private int platform;
    @SerializedName("gameReports")
    private List<SummaryBattleReport> reports;
    @SerializedName("userId")
    private String userId;

    public String getPersonaId() {
        return personaId;
    }

    public int getPlatform() {
        return platform;
    }

    public List<SummaryBattleReport> getReports() {
        return reports;
    }

    public String getUserId() {
        return userId;
    }
}
