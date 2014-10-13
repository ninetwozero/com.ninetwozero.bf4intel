package com.ninetwozero.bf4intel.events;

public class ActiveSoldierChangedEvent {
    private String id;

    public ActiveSoldierChangedEvent(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
