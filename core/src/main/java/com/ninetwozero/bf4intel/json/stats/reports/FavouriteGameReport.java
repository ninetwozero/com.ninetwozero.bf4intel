package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouriteGameReport extends GameReports {

    @SerializedName("teams")
    private Map<Integer, Players> teams = new HashMap<Integer, Players>();



    public Map<Integer, Players> getTeams() {
        return teams;
    }

    @Override
    public boolean isWinner() {
        return false;
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
