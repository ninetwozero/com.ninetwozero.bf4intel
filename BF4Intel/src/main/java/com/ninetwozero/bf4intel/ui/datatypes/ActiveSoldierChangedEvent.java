package com.ninetwozero.bf4intel.ui.datatypes;

public class ActiveSoldierChangedEvent {
    private long id;

    public ActiveSoldierChangedEvent(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}
