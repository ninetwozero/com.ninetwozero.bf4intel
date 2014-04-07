package com.ninetwozero.bf4intel.events.stats.vehicles;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class VehicleStatsRefreshedEvent extends RefreshCompletedEvent {
    public VehicleStatsRefreshedEvent(boolean success) {
        super(success);
    }
}
