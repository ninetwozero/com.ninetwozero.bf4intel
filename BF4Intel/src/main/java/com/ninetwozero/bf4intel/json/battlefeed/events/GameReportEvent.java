package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BestInCategory;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.PersonalHighlight;
import com.ninetwozero.bf4intel.json.battlereports.Team;

import java.util.Map;

public class GameReportEvent extends BaseEvent {
    private String serverName;
    private String map;
    private int gameMode;

    private int playerRanking;
    private int killCount;
    private double kdRatio;
    private int skill;
    private int duration;
    private int score;
    private int spm;

    private Map<String, Team> teams;
    private int playerTeamId;
    private int winningTeamId;

    private BestInCategory bestVehicle;
    private BestInCategory bestWeapon;
    private PersonalHighlight personalHighlight;

    public GameReportEvent(
        final EventType eventType, final String serverName, final String map, final int gameMode, final int playerRanking,
        final int killCount, final double kdRatio, final int skill, final int duration, final int score, final int spm,
        final Map<String, Team> teams, final int playerTeamId, final int winningTeamId,
        final BestInCategory bestVehicle, final BestInCategory bestWeapon, final PersonalHighlight personalHighlight
    ) {
        super(eventType);
        this.serverName = serverName;
        this.map = map;
        this.gameMode = gameMode;
        this.playerRanking = playerRanking;
        this.killCount = killCount;
        this.kdRatio = kdRatio;
        this.skill = skill;
        this.duration = duration;
        this.score = score;
        this.spm = spm;
        this.teams = teams;
        this.playerTeamId = playerTeamId;
        this.winningTeamId = winningTeamId;
        this.bestVehicle = bestVehicle;
        this.bestWeapon = bestWeapon;
        this.personalHighlight = personalHighlight;
    }

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
