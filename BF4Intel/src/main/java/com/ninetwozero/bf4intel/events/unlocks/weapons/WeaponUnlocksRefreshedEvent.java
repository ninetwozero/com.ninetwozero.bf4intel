package com.ninetwozero.bf4intel.events.unlocks.weapons;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class WeaponUnlocksRefreshedEvent extends RefreshCompletedEvent {
    public WeaponUnlocksRefreshedEvent(boolean success) {
        super(success);
    }
}
