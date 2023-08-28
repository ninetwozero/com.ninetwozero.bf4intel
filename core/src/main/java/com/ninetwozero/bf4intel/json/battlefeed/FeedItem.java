package com.ninetwozero.bf4intel.json.battlefeed;

import com.ninetwozero.bf4intel.json.Profile;

public class FeedItem {
    private String id;
    private BaseEvent event;
    private String eventString;
    private String ownerId;
    private Profile owner;
    private Profile owner2;
    private int likeCount;
    private int commentCount;
    private long date;
    private String itemId;


    public FeedItem(
            final String id, final BaseEvent event, final String eventString,
            final String ownerId, final Profile owner, final Profile owner2,
            final int likeCount, final int commentCount, final long date,
            final String itemId
    ) {
        this.id = id;
        this.event = event;
        this.eventString = eventString;
        this.ownerId = ownerId;
        this.owner = owner;
        this.owner2 = owner2;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.date = date;
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public BaseEvent getEvent() {
        return event;
    }

    public String getEventAsString() {
        return eventString;
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

    public String getItemId() {
        return itemId;
    }
}
