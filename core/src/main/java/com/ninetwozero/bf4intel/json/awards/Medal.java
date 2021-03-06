package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Medal implements Comparable<Medal>, Serializable {
    @SerializedName("unlocked")
    private int unlocked;
    @SerializedName("timesTaken")
    private int timesTaken;
    @SerializedName("actualValue")
    private int presentProgress;
    @SerializedName("valueNeeded")
    private int maxProgress;
    @SerializedName("award")
    private MedalAward medalAward;

    public int getUnlocked() {
        return unlocked;
    }

    public int getTimesTaken() {
        return timesTaken;
    }

    public boolean isTaken() { return timesTaken > 0; }

    public int getPresentProgress() {
        return presentProgress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public MedalAward getMedalAward() {
        return medalAward;
    }

    @Override
    public int compareTo(final Medal m) {
        if (presentProgress > m.getPresentProgress()) {
            return -1;
        } else if (presentProgress < m.getPresentProgress()) {
            return 1;
        } else {
            return 0;
        }
    }
}
