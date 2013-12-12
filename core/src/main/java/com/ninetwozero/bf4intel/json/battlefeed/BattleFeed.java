package com.ninetwozero.bf4intel.json.battlefeed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BattleFeed {
    @SerializedName("sessionUserId")
    private String userId;
    @SerializedName("feedEvents")
    private List<FeedItem> feedItems;

    public String getUserId() {
        return userId;
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }
}
