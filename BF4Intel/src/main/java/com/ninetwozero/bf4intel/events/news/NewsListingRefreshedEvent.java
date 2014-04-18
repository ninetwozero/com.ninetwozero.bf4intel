package com.ninetwozero.bf4intel.events.news;


import com.ninetwozero.bf4intel.json.news.NewsListRequest;

public class NewsListingRefreshedEvent {
    private NewsListRequest request;

    public NewsListingRefreshedEvent(NewsListRequest request) {
        this.request = request;
    }

    public NewsListRequest getRequest() {
        return this.request;
    }
}
