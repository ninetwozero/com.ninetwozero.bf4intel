package com.ninetwozero.bf4intel.services.news;

import android.app.Service;
import android.content.Intent;

import com.google.gson.JsonElement;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.events.news.NewsListingRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.news.NewsListRequest;
import com.ninetwozero.bf4intel.network.BaseSimpleRequest;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

public class NewsListingService extends BaseApiService {
    public static final String INTENT_PAGE_ID = "pageId";

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        if (intentCount == 1) {
            final int pageId = intent.getIntExtra(INTENT_PAGE_ID, 0);
            Bf4Intel.getRequestQueue().add(
                new SimpleGetRequest<NewsListRequest>(
                    UrlFactory.buildNewsListingURL(pageId),
                    BaseSimpleRequest.RequestType.FROM_NAVIGATION,
                    this
                ) {
                    @Override
                    protected NewsListRequest doParse(String json) {
                        final JsonElement rootObject = extractFromJson(json, true).getAsJsonObject("context");
                        final NewsListRequest container = gson.fromJson(rootObject, NewsListRequest.class);
                        return container;
                    }

                    @Override
                    protected void deliverResponse(NewsListRequest items) {
                        BusProvider.getInstance().post(new NewsListingRefreshedEvent(items));
                        stopSelf();
                    }
                }
            );
        }
        return Service.START_NOT_STICKY;
    }
}
