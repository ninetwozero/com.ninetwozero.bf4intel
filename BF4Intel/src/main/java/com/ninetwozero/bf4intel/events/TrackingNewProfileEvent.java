package com.ninetwozero.bf4intel.events;

public class TrackingNewProfileEvent {
    private final String userId;
    private final String username;
    private final String gravatarHash;

    public TrackingNewProfileEvent(String userId, String username, String gravatarHash) {
        this.userId = userId;
        this.username = username;
        this.gravatarHash = gravatarHash;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getGravatarHash() {
        return gravatarHash;
    }
}
