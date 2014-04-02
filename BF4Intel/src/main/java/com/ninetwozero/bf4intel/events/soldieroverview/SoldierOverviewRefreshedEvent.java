package com.ninetwozero.bf4intel.events.soldieroverview;

public class SoldierOverviewRefreshedEvent {
    private boolean success;
    public SoldierOverviewRefreshedEvent(boolean success) {
        this.success = success;
    }
}
