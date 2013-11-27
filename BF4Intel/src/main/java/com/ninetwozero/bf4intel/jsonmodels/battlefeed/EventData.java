package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

abstract public class EventData {
    public enum EventType {
        STATUSMESSAGE,
        BECAMEFRIENDS,
        CREATEDFORUMTHREAD,
        WROTEFORUMPOST,
        RECEIVEDWALLPOST,
        RECEIVEDPLATOONWALLPOST,
        ADDEDFAVSERVER,
        RANKEDUP,
        LEVELCOMPLETE,
        CREATEDPLATOON,
        PLATOONBADGESAVED,
        JOINEDPLATOON,
        KICKEDPLATOON,
        LEFTPLATOON,
        GAMEREPORT,
        RECEIVEDAWARD,
        ASSIGNMENTCOMPLETE,
        COMMENTEDGAMEREPORT,
        COMMENTEDBLOG,
        GAMEACCESS,
        SHAREDGAMEEVENT,
        UNKNOWN // OUR CATCH-ALL
    }

    protected String eventName;
    protected EventType eventType;

    public EventData(final String event, final EventType type) {
        this.eventName = event;
        this.eventType = eventType;
    }

    public String getEventName() {
        return this.eventName;
    }
}
