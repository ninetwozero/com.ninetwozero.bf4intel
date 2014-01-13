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

    /*
        Method to remove the last <p> tag, as well as any images as they show up weird
        when processed through Html.fromHtml(String)
     */
    public String fetchTrimmedContent() {
        final StringBuilder builder = new StringBuilder(content.replaceAll("<p>&nbsp;</p>", ""));
        final int positionOfImage = builder.lastIndexOf("<img");
        if (positionOfImage != -1) {
            int endTagOffset = builder.substring(positionOfImage).indexOf("/>");
            int endTagPosition = endTagOffset + positionOfImage + 2;
            if (endTagPosition != -1) {
                builder.replace(
                    positionOfImage,
                    endTagPosition,
                    ""
                );
            }
        }

        final int positionOfLastParagraph = builder.lastIndexOf("<p>");
        if (positionOfLastParagraph != -1) {
            builder.replace(
                positionOfLastParagraph,
                positionOfLastParagraph + 3,
                ""
            );
        }
        return builder.toString();
    }
}