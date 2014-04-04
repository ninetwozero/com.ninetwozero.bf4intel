package com.ninetwozero.bf4intel.json;

import com.google.gson.annotations.SerializedName;

public class StickyHeaderItem {
    @SerializedName("title")
    private String title;
    @SerializedName("childCount")
    private int childCount;

    public StickyHeaderItem(String title, int childCount) {
        this.title = title;
        this.childCount = childCount;
    }

    public String getTitle() {
        return title;
    }

    public int getChildCount() {
        return childCount;
    }
}
