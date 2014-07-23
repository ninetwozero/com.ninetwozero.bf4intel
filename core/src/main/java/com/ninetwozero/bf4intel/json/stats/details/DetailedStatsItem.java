package com.ninetwozero.bf4intel.json.stats.details;

import com.google.gson.annotations.SerializedName;

public class DetailedStatsItem {
    @SerializedName("key")
    private String key;
    @SerializedName("value")
    private String value;

    public DetailedStatsItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
