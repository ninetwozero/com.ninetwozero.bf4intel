package com.ninetwozero.bf4intel.services.battlefeed;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.events.battlefeed.BattleFeedRefreshedEvent;
import com.ninetwozero.bf4intel.network.Bf4IntelService;
import com.ninetwozero.bf4intel.json.battlefeed.BattleFeed;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

public class BattleFeedService extends BaseApiService {
    public static final String INTENT_COUNT = "count";
    public static final String INTENT_USERID = "userId";

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        if (intentCount == 1) {
            final int count = intent.getIntExtra(INTENT_COUNT, 0);
            final String userId = intent.getStringExtra(INTENT_USERID);
            final boolean fetchGlobalFeed = userId == null || userId.equals("");

            Bf4Intel.getRequestQueue().add(
                new SimpleGetRequest<BattleFeed>(
                    fetchGlobalFeed ? Bf4IntelService.buildGlobalFeedURL(count) : Bf4IntelService.buildUserFeedURL(userId, count),
                    this
                ) {
                    @Override
                    protected BattleFeed doParse(String json) {
                        final BattleFeed battleFeed = fromJson(json, BattleFeed.class);
                        return battleFeed;
                    }

                    @Override
                    protected void deliverResponse(BattleFeed items) {
                        BusProvider.getInstance().post(new BattleFeedRefreshedEvent(items));
                        stopSelf();
                    }
                }
            );
        }
        return Service.START_NOT_STICKY;
    }
}
