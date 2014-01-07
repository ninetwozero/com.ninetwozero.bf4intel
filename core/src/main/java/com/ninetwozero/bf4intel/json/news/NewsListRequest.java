package com.ninetwozero.bf4intel.json.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListRequest {
    @SerializedName("page")
    private int pageId;
    @SerializedName("blogPosts")
    private List<NewsArticle> articles;
    @SerializedName("pages")
    private int pageCount;

    public int getPageId() {
        return pageId;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public int getPageCount() {
        return pageCount;
    }
}
