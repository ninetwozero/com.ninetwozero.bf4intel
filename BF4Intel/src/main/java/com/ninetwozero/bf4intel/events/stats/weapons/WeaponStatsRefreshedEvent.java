package com.ninetwozero.bf4intel.events.stats.weapons;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class WeaponStatsRefreshedEvent extends RefreshCompletedEvent {
    public WeaponStatsRefreshedEvent(boolean result) {
        super(result);
    }
}
