package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouriteGameReport extends GameReport {

    @SerializedName("teams")
    private Map<Integer, Players> teams = new HashMap<Integer, Players>();

    public Map<Integer, Players> getTeams() {
        return teams;
    }

    @Override
    public MatchResult matchResult(int soldierId) {
        if (getWinner() == RESULT_DRAW) {
            return MatchResult.DRAW;
        } else {
            List<Integer> winningTeam = teams.get(getWinner()).getPlayersList();
            return winningTeam.contains(soldierId) ? MatchResult.WON : MatchResult.LOST;
        }
    }

    public class Players {

        @SerializedName("players")
        private List<Integer> playersList = new ArrayList<Integer>();
        @SerializedName("score")
        private int score;

        public List<Integer> getPlayersList() {
            return playersList;
        }

        public int getScore() {
            return score;
        }
    }
}
