package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

public class FeedItemElement {
    private int resourceId;
    private String content;
    private boolean image;

    public FeedItemElement(final int resourceId, final String content, final boolean image) {
        this.resourceId = resourceId;
        this.content = content;
        this.image = image;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getContent() {
        return content;
    }

    public boolean isImage() {
        return image;
    }
}
