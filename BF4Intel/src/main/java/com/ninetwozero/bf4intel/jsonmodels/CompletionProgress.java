package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class CompletionProgress {
    @SerializedName("name")
    private String mName;

    @SerializedName("current")
    private int mCurrentValue;

    @SerializedName("max")
    private int mMaxValue;

    @SerializedName("percent")
    private int mPercent;

    public CompletionProgress(final String name, final int current, final int max, final int percent) {
        mName = name;
        mCurrentValue = current;
        mMaxValue = max;
        mPercent = percent;
    }

    public String getName() {
        return mName;
    }

    public int getCurrentValue() {
        return mCurrentValue;
    }

    public int getMaxValue() {
        return mMaxValue;
    }

    public int getPercent() {
        return mPercent;
    }
}
