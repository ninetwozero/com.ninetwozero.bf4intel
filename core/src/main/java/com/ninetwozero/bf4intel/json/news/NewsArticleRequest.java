package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class NewsArticleRequest {
    @SerializedName("blogPost")
    private NewsArticle article;
    @SerializedName("canPost")
    private boolean allowPosting;
    @SerializedName("canDelete")
    private boolean allowDeleting;
    @SerializedName("canBan")
    private boolean allowBanning;
    @SerializedName("hasVotedOnComments")
    private Map<String, Boolean> hooahStatus;

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

    public Map<String, Boolean> getHooahStatus() {
        return hooahStatus;
    }
}
