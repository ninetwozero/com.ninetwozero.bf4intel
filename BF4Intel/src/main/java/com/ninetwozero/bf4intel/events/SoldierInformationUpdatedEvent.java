package com.ninetwozero.bf4intel.events;

import android.os.Bundle;

public class SoldierInformationUpdatedEvent {
    private Bundle soldier;

    public SoldierInformationUpdatedEvent(Bundle soldier) {
        this.soldier = soldier;
    }

    public Bundle getSoldier() {
        return soldier;
    }
}
