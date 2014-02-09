package com.ninetwozero.bf4intel.json.login;

import com.google.gson.annotations.SerializedName;

public class SummarizedSoldierStats {
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

    // Persona related stuff:
    private long _id;
    private String personaName;
    private String tag;
    private String picture;
    private String userId;

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

    /* Persona related stuff */

    public long getId() {
        return _id;
    }

    public String getPersonaName() {
        return personaName;
    }

    public String getTag() {
        return tag;
    }

    public String getPersonaPicture() {
        return picture;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(long id) {
        this._id = id;
    }

    public void setPersonaName(String personaName) {
        this.personaName = personaName;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
