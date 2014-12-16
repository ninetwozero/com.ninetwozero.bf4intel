package com.ninetwozero.bf4intel.events;

import android.os.Bundle;

public class TrackingNewSoldierEvent {
    private Bundle extras;

    public TrackingNewSoldierEvent(Bundle extras) {
        this.extras = extras;
    }

    public Bundle getExtras() {
        return extras;
    }
}
