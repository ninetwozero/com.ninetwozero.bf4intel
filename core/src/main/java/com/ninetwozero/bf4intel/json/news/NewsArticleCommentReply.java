package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;

public class NewsArticleCommentReply {
    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private Profile author;
    @SerializedName("body")
    private String content;
    @SerializedName("creationDate")
    private long timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isHidden() {
        return hidden;
    }
}
