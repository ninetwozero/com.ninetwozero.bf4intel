package com.ninetwozero.bf4intel.utils;

import android.os.Bundle;

public class SoldierInformationUpdated {
    private Bundle soldier;

    public SoldierInformationUpdated(Bundle soldier) {
        this.soldier = soldier;
    }

    public Bundle getSoldier() {
        return soldier;
    }
}
