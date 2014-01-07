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

    public long getTimestamp() {
        return timestamp;
    }

    public List<NewsArticleCommentReply> getReplies() {
        return replies;
    }

    public boolean isHidden() {
        return hidden;
    }

    @Override
    public String toString() {
        return "NewsArticleComment{" +
            "id='" + id + '\'' +
            ", author=" + author +
            ", content='" + content + '\'' +
            ", likeCount=" + likeCount +
            ", timestamp=" + timestamp +
            ", replies=" + replies +
            ", hidden=" + hidden +
            '}';
    }
}