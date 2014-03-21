package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class TopLeaderboardItem implements Comparable<TopLeaderboardItem> {
    @SerializedName("statId")
    private String statId;

    @SerializedName("division")
    private int division;

    @SerializedName("score")
    private double score;

    @SerializedName("area")
    private LeaderboardArea area;

    @SerializedName("leaderboardItem")
    private LeaderboardItemInformation information;

    public String getStatId() {
        return statId;
    }

    public int getDivision() {
        return division;
    }

    public double getScore() {
        return score;
    }

    public LeaderboardArea getArea() {
        return area;
    }

    public LeaderboardItemInformation getInformation() {
        return information;
    }

    @Override
    public int compareTo(final TopLeaderboardItem item) {
        if (division < item.getDivision()) {
            return -1;
        } else if (division > item.getDivision()) {
            return 1;
        } else {
            return information.getName().compareToIgnoreCase(item.getInformation().getName());
        }
    }
}
