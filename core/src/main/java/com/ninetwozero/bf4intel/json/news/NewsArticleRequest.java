package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;

public class NewsArticleRequest {
    @SerializedName("blogPost")
    private NewsArticle article;
    @SerializedName("canPost")
    private boolean allowPosting;
    @SerializedName("canDelete")
    private boolean allowDeleting;
    @SerializedName("canBan")
    private boolean allowBanning;

    public NewsArticle getArticle() {
        return article;
    }

    public boolean isAllowPosting() {
        return allowPosting;
    }

    public boolean isAllowDeleting() {
        return allowDeleting;
    }

    public boolean isAllowBanning() {
        return allowBanning;
    }
}
