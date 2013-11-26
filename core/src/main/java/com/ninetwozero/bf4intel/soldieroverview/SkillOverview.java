package com.ninetwozero.bf4intel.soldieroverview;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class SkillOverview {
    @SerializedName("skill")
    private String skillRating;

    @SerializedName("kills")
    private long killCount;

    @SerializedName("kdRatio")
    private double killDeathRatio;

    @SerializedName("timePlayed")
    private int timePlayed;

    @SerializedName("score")
    private int score;

    @SerializedName("scorePerMinute")
    private int scorePerMinute;

    @SerializedName("serviceStars")
    private Map<Integer, Integer> serviceStars;

    @SerializedName("serviceStarsProgress")
    private Map<Integer, Double> serviceStarProgress;

    public String getSkillRating() {
        return skillRating;
    }

    public long getKillCount() {
        return killCount;
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

    public int getScorePerMinute() {
        return scorePerMinute;
    }

    public double getKillsPerMinute() {
        return ((double) killCount) / (timePlayed / 60);
    }

    public Map<Integer, Integer> getServiceStars() {
        return serviceStars;
    }

    public Map<Integer, Double> getServiceStarProgress() {
        return serviceStarProgress;
    }
}
