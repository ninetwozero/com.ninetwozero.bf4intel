package com.ninetwozero.bf4intel.events.soldieroverview;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class SoldierOverviewRefreshedEvent extends RefreshCompletedEvent {
    public SoldierOverviewRefreshedEvent(final boolean success) {
        super(success);
    }
}
