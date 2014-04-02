package com.ninetwozero.bf4intel.events;

public class RefreshCompletedEvent {
    private boolean success;

    public RefreshCompletedEvent(boolean success) {
        this.success = success;
    }

    public boolean isSuccessful() {
        return this.success;
    }
}
