package com.ninetwozero.bf4intel.json.login;

import com.google.gson.annotations.SerializedName;

public class SummarizedSoldierStats {
    @SerializedName("persona")
    private Persona persona;
    @SerializedName("rank")
    private int rank;
    @SerializedName("skill")
    private int skill;
    @SerializedName("kills")
    private int killCount;
    @SerializedName("deaths")
    private int deathCount;
    @SerializedName("numWins")
    private int winCount;
    @SerializedName("numLosses")
    private int lossCount;
    @SerializedName("score")
    private long score;
    @SerializedName("timePlayed")
    private int duration;
    @SerializedName("platform")
    private int platformId;
    @SerializedName("game")
    private int gameId;

    public Persona getPersona() {
        return persona;
    }

    public int getRank() {
        return rank;
    }

    public int getSkill() {
        return skill;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLossCount() {
        return lossCount;
    }

    public long getScore() {
        return score;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlatformId() {
        return platformId;
    }

    public int getGameId() {
        return gameId;
    }
}
