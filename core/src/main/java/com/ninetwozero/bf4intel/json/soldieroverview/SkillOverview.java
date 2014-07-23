package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class SkillOverview {
    @SerializedName("skill")
    private String skillRating;

    @SerializedName("kills")
    private int killCount;

    @SerializedName("deaths")
    private int deathCount;

    @SerializedName("kdRatio")
    private double killDeathRatio;

    @SerializedName("timePlayed")
    private int timePlayed;

    @SerializedName("score")
    private int score;

    @SerializedName("rankScore")
    private int rankScore;

    @SerializedName("scorePerMinute")
    private int scorePerMinute;

    @SerializedName("serviceStars")
    private Map<Integer, Integer> serviceStars;

    @SerializedName("serviceStarsProgress")
    private Map<Integer, Double> serviceStarProgress;

    @SerializedName("serviceStarsGameModes")
    private List<GameModeServiceStar> gameModeServiceStars;

    public String getSkillRating() {
        return skillRating;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public double getKillDeathRatio() {
        return killDeathRatio;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public int getScore() {
        return score;
    }

    public int getRankScore() {
        return rankScore;
    }

    public int getScorePerMinute() {
        return scorePerMinute;
    }

    public double getKillsPerMinute() {
        return ((double) killCount) / ((double)timePlayed / 60);
    }

    public Map<Integer, Integer> getServiceStars() {
        return serviceStars;
    }

    public Map<Integer, Double> getServiceStarProgress() {
        return serviceStarProgress;
    }

    public List<GameModeServiceStar> getGameModeServiceStars() {
        return gameModeServiceStars;
    }
}
