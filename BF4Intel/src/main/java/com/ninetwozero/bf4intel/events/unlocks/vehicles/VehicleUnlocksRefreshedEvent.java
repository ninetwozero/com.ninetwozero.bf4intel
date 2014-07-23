package com.ninetwozero.bf4intel.events.unlocks.vehicles;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class VehicleUnlocksRefreshedEvent extends RefreshCompletedEvent {
    public VehicleUnlocksRefreshedEvent(boolean success) {
        super(success);
    }
}
