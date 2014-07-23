package com.ninetwozero.bf4intel.events.battlefeed;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;
import com.ninetwozero.bf4intel.json.battlefeed.BattleFeed;

public class BattleFeedRefreshedEvent {
    private BattleFeed feed;
    public BattleFeedRefreshedEvent(BattleFeed feed) {
        this.feed = feed;
    }

    public BattleFeed getFeed() {
        return feed;
    }
}
