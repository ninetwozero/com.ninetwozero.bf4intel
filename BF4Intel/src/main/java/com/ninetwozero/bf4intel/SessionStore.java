package com.ninetwozero.bf4intel;

public class SessionStore {
    private static String sessionId = "<YOUR COOKIE VALUE HERE>";
    private static String username;
    private static String userId;
    private static String checksum = "<YOUR CHECKSUM HERE>";
    private static String gravatarHash;

    public static String getSessionId() {
        return sessionId;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getChecksum() {
        return checksum;
    }

    public static String getGravatarHash() {
        return gravatarHash;
    }

    public static boolean isLoggedIn() {
        return false;
    }

    public static boolean hasUserId() {
        return userId != null;
    }

    /* Session modification */
    public static void load(final String sessionId, final String userId, final String username, final String gravatarHash) {
        SessionStore.sessionId = sessionId;
        SessionStore.userId = userId;
        SessionStore.username = username;
        SessionStore.gravatarHash = gravatarHash;
    }
}
