package com.ninetwozero.bf4intel.services.news;

import android.app.Service;
import android.content.Intent;

import com.google.gson.JsonElement;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.events.news.NewsArticleRefreshedEvent;
import com.ninetwozero.bf4intel.network.Bf4IntelService;
import com.ninetwozero.bf4intel.json.news.NewsArticleRequest;
import com.ninetwozero.bf4intel.network.BaseSimpleRequest;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.net.URL;

public class NewsArticleService extends BaseApiService {
    public static final String INTENT_ARTICLE_ID = "articleId";

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        if (intentCount == 1) {
            final URL url = Bf4IntelService.buildNewsArticleURL(intent.getStringExtra(INTENT_ARTICLE_ID));
            Bf4Intel.getRequestQueue().add(
                new SimpleGetRequest<NewsArticleRequest>(
                    url,
                    BaseSimpleRequest.RequestType.FROM_NAVIGATION,
                    this
                ) {
                    @Override
                    protected NewsArticleRequest doParse(String json) {
                        final JsonElement rootObject = extractFromJson(json, true).getAsJsonObject().get("context");
                        final NewsArticleRequest articleRequest = gson.fromJson(rootObject, NewsArticleRequest.class);
                        return articleRequest;
                    }

                    @Override
                    protected void deliverResponse(NewsArticleRequest article) {
                        BusProvider.getInstance().post(new NewsArticleRefreshedEvent(article));
                        stopSelf();
                    }
                }
            );
        }
        return Service.START_NOT_STICKY;
    }
}
