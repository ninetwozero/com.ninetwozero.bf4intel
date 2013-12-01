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

    public int getUnlocked() {
        return unlocked;
    }

    public int getTimesTaken() {
        return timesTaken;
    }

    public int getPresentProgress() {
        return presentProgress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }
}
