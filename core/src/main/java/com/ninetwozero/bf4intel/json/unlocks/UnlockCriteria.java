package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class UnlockCriteria implements Comparable<UnlockCriteria> {
    public static final int HUNDRED_PERCENTS = 100;
    @SerializedName("codeNeeded")
    private String label;
    @SerializedName("unlockType")
    private String type;
    @SerializedName("actualValue")
    private int currentValue;
    @SerializedName("valueNeeded")
    private int targetValue;
    @SerializedName("completion")
    private int completion;
    @SerializedName("completed")
    private boolean completed;

    // Only present in special cases
    @SerializedName("award")
    private CriteriaAward award;

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

    public int getCompletion() {
        return completion;
    }

    public boolean isCompleted() {
        return completed;
    }

    public CriteriaAward getAward() {
        return award;
    }

    @Override
    public int compareTo(final UnlockCriteria other) {
        final int completion1 = completion;
        final int completion2 = other.getCompletion();

        if ((completion1 < HUNDRED_PERCENTS && completion2 < HUNDRED_PERCENTS)) {
            if (completion1 > completion2) {
                return -1;
            } else if (completion1 < completion2) {
                return 1;
            }
        } else if (completion1 == completion2) {
            return 0;
        } else {
            if (completion1 == HUNDRED_PERCENTS) {
                return 1;
            } else if (completion2 == HUNDRED_PERCENTS) {
                return -1;
            }
        }
        return 0;
    }

    public boolean isScoreCriteria() {
        return award == null;
    }
}
