package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CommentedBlogEvent extends BaseEvent {
    @SerializedName("blogTitle")
    private String blogTitle;
    @SerializedName("blogCommentBody")
    private String comment;

    public String getBlogTitle() {
        return this.blogTitle;
    }

    public String getComment() {
        return this.comment;
    }
}
