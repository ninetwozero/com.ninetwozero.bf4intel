package com.ninetwozero.bf4intel.jsonmodels.soldieroverview;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.Skill;
import com.ninetwozero.bf4intel.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
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
        return((double) killCount)/(timePlayed / 60);
    }

    public Map<Integer, Integer> getServiceStars() {
        return serviceStars;
    }

    public Map<Integer, Double> getServiceStarProgress() {
        return serviceStarProgress;
    }

    public List<Skill> asList() {
        final List<Skill> skillList = new ArrayList<Skill>(6);
        skillList.add(new Skill(R.string.skills_kd, killDeathRatio));
        skillList.add(new Skill(R.string.skills_spm, scorePerMinute));
        skillList.add(new Skill(R.string.skills_kpm, String.format("%.2f", getKillsPerMinute())));
        skillList.add(new Skill(R.string.skills_kills, killCount));
        skillList.add(new Skill(R.string.skills_score, String.format("%,d", score)));
        skillList.add(new Skill(R.string.skills_time, DateUtils.toLiteral(timePlayed)));
        return skillList;
    }

    @Override
    public String toString() {
        return "SkillOverview{" +
            "skillRating='" + skillRating + '\'' +
            ", killCount=" + killCount +
            ", killDeathRatio=" + killDeathRatio +
            ", timePlayed=" + timePlayed +
            ", score=" + score +
            ", scorePerMinute=" + scorePerMinute +
            ", serviceStars=" + serviceStars +
            ", serviceStarProgress=" + serviceStarProgress +
            '}';
    }
}
