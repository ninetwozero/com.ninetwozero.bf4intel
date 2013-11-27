package com.ninetwozero.bf4intel.json.battlereports;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("score")
    private int finalscore;

    @SerializedName("scoreMax")
    private int startingScore;

    @SerializedName("isWinner")
    private boolean winner;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFinalscore() {
        return finalscore;
    }

    public int getStartingScore() {
        return startingScore;
    }

    public boolean isWinner() {
        return winner;
    }
    /*
    @SerializedName("players")
    private List<Player> players;

    @SerializedName("commanders")
    private List<Player> commanders;

    @SerializedName("squads")
    private List<Squad> squads;
*/
}
