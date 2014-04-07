package com.ninetwozero.bf4intel.events.unlocks.kits;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class KitUnlocksRefreshedEvent extends RefreshCompletedEvent {
    public KitUnlocksRefreshedEvent(boolean result) {
        super(result);
    }
}
