package com.ninetwozero.bf4intel.events;

public class ActiveSoldierChangedEvent {
    private long id;

    public ActiveSoldierChangedEvent(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}
