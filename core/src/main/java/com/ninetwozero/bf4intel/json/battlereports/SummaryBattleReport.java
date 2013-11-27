package com.ninetwozero.bf4intel.json.battlereports;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class SummaryBattleReport {
    @SerializedName("gameReportId")
    private String id;

    @SerializedName("name")
    private String serverName;

    @SerializedName("map")
    private String map;

    @SerializedName("gameMode")
    private int gameMode;

    @SerializedName("teams")
    private Map<String, Team> teams;

    @SerializedName("playerTeams")
    private Map<String, String[]> playersTeams;

    @SerializedName("winner")
    private int winner;

    @SerializedName("duration")
    private int duration;

    @SerializedName("createdAt")
    private long date;

    public String getId() {
        return id;
    }

    public String getServerName() {
        return serverName;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public String getMap() {
        return map;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getWinner() {
        return winner;
    }

    public Map<String, String[]> getPlayers() { return playersTeams; }

    public int getDuration() {
        return duration;
    }

    public long getDate() {
        return date;
    }

    public boolean isPlayerInTeam(final String personaId, final Object teamId) {
        final String[] players = playersTeams.get(String.valueOf(teamId));
        for (String player : players) {
            if (player.equals(personaId)) {
                return true;
            }
        }
        return false;
    }
}
