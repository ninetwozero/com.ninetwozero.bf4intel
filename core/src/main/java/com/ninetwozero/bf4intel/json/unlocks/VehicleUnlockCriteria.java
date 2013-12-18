package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class VehicleUnlockCriteria {
    @SerializedName("codeNeeded")
    private String label;
    @SerializedName("unlockType")
    private String type;
    @SerializedName("actualValue")
    private int currentValue;
    @SerializedName("valueNeeded")
    private int targetValue;
    @SerializedName("completion")
    private double completion;
    @SerializedName("completed")
    private boolean completed;

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public double getCompletion() {
        return completion;
    }

    public boolean isCompleted() {
        return completed;
    }

    public double getCalculatedCompletion() {
        return currentValue / (targetValue*1.0);
    }
}
