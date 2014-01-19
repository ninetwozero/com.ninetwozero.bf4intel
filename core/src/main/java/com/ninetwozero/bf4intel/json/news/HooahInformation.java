package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;

public class HooahInformation {
    @SerializedName("votes")
    private int voteCount;
    @SerializedName("userVoted")
    private boolean voted;

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVoted() {
        return voted;
    }
}
