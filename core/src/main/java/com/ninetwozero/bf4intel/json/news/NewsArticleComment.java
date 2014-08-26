package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;

import java.util.List;

public class NewsArticleComment {
    @SerializedName("id")
    private String id;
    @SerializedName("user")
    private Profile author;
    @SerializedName("body")
    private String content;
    @SerializedName("points")
    private int likeCount;
    @SerializedName("creationDate")
    private long timestamp;
    @SerializedName("replies")
    private List<NewsArticleCommentReply> replies;
    @SerializedName("hidden")
    private boolean hidden;

    public NewsArticleComment(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Profile getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getReplyCount() {
        return replies != null ? replies.size() : 0;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean hasReplies() {
        return replies != null && replies.size() > 0;
    }

    public List<NewsArticleCommentReply> getReplies() {
        return replies;
    }

    public boolean isHidden() {
        return hidden;
    }
}
