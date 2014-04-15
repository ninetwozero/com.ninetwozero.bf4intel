package com.ninetwozero.bf4intel.events.news;

import com.ninetwozero.bf4intel.json.news.NewsArticleRequest;

public class NewsArticleRefreshedEvent {
    private NewsArticleRequest request;

    public NewsArticleRefreshedEvent(NewsArticleRequest request) {
        this.request = request;
    }

    public NewsArticleRequest getRequest() {
        return request;
    }
}
