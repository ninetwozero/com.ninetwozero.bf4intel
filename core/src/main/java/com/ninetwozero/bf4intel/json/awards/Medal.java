package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

public class Medal {
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
}
