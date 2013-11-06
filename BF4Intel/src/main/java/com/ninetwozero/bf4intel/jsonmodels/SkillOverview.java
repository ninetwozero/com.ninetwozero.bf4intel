package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.Skill;
import com.ninetwozero.bf4intel.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkillOverview {
    @SerializedName("skill")
    private String mSkillRating;

    @SerializedName("kills")
    private long mKillCount;

    @SerializedName("kdRatio")
    private double mKillDeathRatio;

    @SerializedName("timePlayed")
    private int mTimePlayed;

    @SerializedName("score")
    private int mScore;

    @SerializedName("scorePerMinute")
    private int mScorePerMinute;

    @SerializedName("serviceStars")
    private Map<Integer, Integer> mServiceStars;

    @SerializedName("serviceStarsProgress")
    private Map<Integer, Double> mServiceStarProgress;

    public String getSkillRating() {
        return mSkillRating;
    }

    public long getKillCount() {
        return mKillCount;
    }

    public double getKillDeathRatio() {
        return mKillDeathRatio;
    }

    public int getTimePlayed() {
        return mTimePlayed;
    }

    public int getScore() {
        return mScore;
    }

    public int getScorePerMinute() {
        return mScorePerMinute;
    }

    public double getKillsPerMinute() {
        return((double) mKillCount)/(mTimePlayed / 60);
    }

    public Map<Integer, Integer> getServiceStars() {
        return mServiceStars;
    }

    public Map<Integer, Double> getServiceStarProgress() {
        return mServiceStarProgress;
    }

    public List<Skill> asList() {
        final List<Skill> skillList = new ArrayList<Skill>(6);
        skillList.add(new Skill(R.string.skills_kd, mKillDeathRatio));
        skillList.add(new Skill(R.string.skills_spm, mScorePerMinute));
        skillList.add(new Skill(R.string.skills_kpm, String.format("%.2f", getKillsPerMinute())));
        skillList.add(new Skill(R.string.skills_kills, mKillCount));
        skillList.add(new Skill(R.string.skills_score, String.format("%,d", mScore)));
        skillList.add(new Skill(R.string.skills_time, DateUtils.toLiteral(mTimePlayed)));
        return skillList;
    }
}
