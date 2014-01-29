package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsGameReport extends GameReport {

    @SerializedName("playerTeams")
    private Map<Integer, List<Integer>> teamPlayers = new HashMap<Integer, List<Integer>>();
    @SerializedName("mapModeResult")
    private MapModeResult mapModeResult;

    public Map<Integer, List<Integer>> getTeamPlayers() {
        return teamPlayers;
    }

    public MapModeResult getMapModeResult() {
        return mapModeResult;
    }

    @Override
    public MatchResult findMatchResultFor(int soldierId) {
        if (getWinner() == RESULT_DRAW) {
            return MatchResult.DRAW;
        } else {
            List<Integer> winningTeam = teamPlayers.get(getWinner());
            return winningTeam.contains(soldierId) ? MatchResult.WON : MatchResult.LOST;
        }
    }

    public class MapModeResult {
        @SerializedName("ticket")
        private Map<Integer, Integer> teamTickets = new HashMap<Integer, Integer>();
    }
}
