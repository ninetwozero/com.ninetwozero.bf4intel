package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

public class Ribbon {
    @SerializedName("unlocked")
    private int unlocked;
    @SerializedName("timesTaken")
    private int timesTaken;

    public int getUnlocked() {
        return unlocked;
    }

    public int getTimesTaken() {
        return timesTaken;
    }

    public boolean isTaken() { return timesTaken > 0; }
}
