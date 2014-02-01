package com.ninetwozero.bf4intel;

public class SessionStore {
    private static final String sessionId = "<YOUR COOKIE VALUE HERE>";

    public static String getChecksum() {
        return "<YOUR CHECKSUM HERE>";
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static boolean isLoggedIn() {
        return false;
    }
}
