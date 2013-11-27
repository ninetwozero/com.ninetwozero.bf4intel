package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

public class FeedItem {
    private String id;
    private EventData event;
    private String ownerId;
    private Profile owner;
    private Profile owner2;
    private int likeCount;
    private int commentCount;
    private long date;


    public FeedItem(
        final String id, final EventData event, final String ownerId,
        final Profile owner, final Profile owner2,
        final int likeCount, final int commentCount, final long date
    ) {
        this.id = id;
        this.event = event;
        this.ownerId = ownerId;
        this.owner = owner;
        this.owner2 = owner2;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public EventData getEvent() {
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
