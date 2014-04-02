package com.ninetwozero.bf4intel.events.awards;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class AwardsRefreshedEvent extends RefreshCompletedEvent {
    public AwardsRefreshedEvent(boolean result) {
        super(result);
    }
}
