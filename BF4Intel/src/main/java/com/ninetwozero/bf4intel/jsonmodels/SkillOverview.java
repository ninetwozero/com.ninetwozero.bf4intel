package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class SkillOverview {
    @SerializedName("skill")
    private String mSkillRating;

    @SerializedName("kills")
    private long mKillCount;

    @SerializedName("kdRatio")
    private double mKillDeathRatio;

    @SerializedName("timePlayed")
    private long mTimePlayed;

    @SerializedName("score")
    private int mScore;

    @SerializedName("scorePerMinute")
    private int mScorePerMinute;

    public SkillOverview(final String skillRating, final long killCount, final double killDeathRatio, final long timePlayed, final int score, final int scorePerMinute) {
        mSkillRating = skillRating;
        mKillCount = killCount;
        mKillDeathRatio = killDeathRatio;
        mTimePlayed = timePlayed;
        mScore = score;
        mScorePerMinute = scorePerMinute;
    }

    public String getSkillRating() {
        return mSkillRating;
    }

    public long getKillCount() {
        return mKillCount;
    }

    public double getKillDeathRatio() {
        return mKillDeathRatio;
    }

    public long getTimePlayed() {
        return mTimePlayed;
    }

    public int getScore() {
        return mScore;
    }

    public int getScorePerMinute() {
        return mScorePerMinute;
    }

    public double getKillsPerMinute() {
        return ((double) mKillCount)/mTimePlayed;
    }
}
