package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.BestInCategory;
import com.ninetwozero.bf4intel.json.battlefeed.PersonalHighlight;
import com.ninetwozero.bf4intel.json.battlereports.Team;

import java.util.Map;

public class GameReportEvent extends BaseEvent {
    @SerializedName("serverName")
    private String serverName;
    @SerializedName("map")
    private String map;
    @SerializedName("gameMode")
    private int gameMode;

    @SerializedName("position")
    private int playerRanking;
    @SerializedName("kills")
    private int killCount;
    @SerializedName("kdr")
    private double kdRatio;
    @SerializedName("skill")
    private int skill;
    @SerializedName("duration")
    private int duration;
    @SerializedName("score")
    private int score;
    @SerializedName("spm")
    private int spm;

    @SerializedName("teamScores")
    private Map<String, Team> teams;
    @SerializedName("playerTeam")
    private int playerTeamId;
    @SerializedName("winTeam")
    private int winningTeamId;

    @SerializedName("bestVehicle")
    private BestInCategory bestVehicle;
    @SerializedName("bestWeapon")
    private BestInCategory bestWeapon;
    @SerializedName("personalPrize")
    private PersonalHighlight personalHighlight;

    public String getServerName() {
        return serverName;
    }

    public String getMap() {
        return map;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getPlayerRanking() {
        return playerRanking;
    }

    public int getKillCount() {
        return killCount;
    }

    public double getKdRatio() {
        return kdRatio;
    }

    public int getSkill() {
        return skill;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }

    public int getSpm() {
        return spm;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public int getPlayerTeamId() {
        return playerTeamId;
    }

    public int getWinningTeamId() {
        return winningTeamId;
    }

    public BestInCategory getBestVehicle() {
        return bestVehicle;
    }

    public BestInCategory getBestWeapon() {
        return bestWeapon;
    }

    public PersonalHighlight getPersonalHighlight() {
        return personalHighlight;
    }
}
