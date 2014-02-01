package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;

import java.util.List;

public class NewsArticle {
    @SerializedName("id")
    private String id;
    @SerializedName("slug")
    private String urlSlug;
    @SerializedName("user")
    private Profile author;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("creationDate")
    private long timestamp;
    @SerializedName("thumbnail")
    private String thumbnailUrl;
    @SerializedName("votes")
    private int hooahCount;
    @SerializedName("devblogCommentCount")
    private int commentCount;
    @SerializedName("topstory")
    private int topPost;
    @SerializedName("highlight")
    private int hightlightedPost;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("userVoted")
    private boolean userSaidHooah;
    @SerializedName("comments")
    private List<NewsArticleComment> comments;

    public String getId() {
        return id;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public boolean hasAuthor() {
        return author != null;
    }

    public Profile getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getHooahCount() {
        return hooahCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getTopPost() {
        return topPost;
    }

    public int getHightlightedPost() {
        return hightlightedPost;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean hasUserSaidHooah() {
        return userSaidHooah;
    }

    public boolean hasComments() {
        return comments != null && comments.size() > 0;
    }

    public List<NewsArticleComment> getComments() {
        return comments;
    }
}
