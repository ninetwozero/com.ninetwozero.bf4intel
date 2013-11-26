package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

import com.google.gson.annotations.SerializedName;

public class FeedItem {
    @SerializedName("id")
    private String id;

    @SerializedName("event")
    private String event;

    @SerializedName("ownerId")
    private String ownerId;

    @SerializedName("owner")
    private Profile owner;

    @SerializedName("owner2")
    private Profile owner2;

    @SerializedName("numLikes")
    private int likeCount;

    @SerializedName("numComments")
    private int commentCount;

    @SerializedName("creationDate")
    private long date;

    public String getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Profile getOwner() {
        return owner;
    }

    public Profile getOwner2() {
        return owner2;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public long getDate() {
        return date;
    }
}
