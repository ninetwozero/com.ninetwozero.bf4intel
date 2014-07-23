package com.ninetwozero.bf4intel.events.stats.details;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class DetailedStatsRefreshedEvent extends RefreshCompletedEvent {
    public DetailedStatsRefreshedEvent(boolean result) {
        super(result);
    }
}
