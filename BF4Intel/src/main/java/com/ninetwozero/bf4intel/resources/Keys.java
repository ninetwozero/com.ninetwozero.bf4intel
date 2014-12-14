package com.ninetwozero.bf4intel.resources;

public class Keys {
    public static final String CHECKSUM = "post-check-sum";
    public static final String SESSION_ID = "sessionId";

    public static class Soldier {
        public static final String ID = "soldierId";
        public static final String NAME = "soldierName";
        public static final String PLATFORM = "platformId";
        public static final String AVATAR = "avatar";
    }

    public static class Profile {
        public static final String ID = "profileId";
        public static final String USERNAME = "username";
        public static final String NAME = "name";
        public static final String GRAVATAR_HASH = "gravatarHash";
    }

    public static class Menu {
        public static final String LATEST_PERSONA = "last_selected_soldier_in_menu";
        public static final String LATEST_PERSONA_PLATFORM = "last_selected_soldier_platform_in_menu";
    }

    public class Settings {
        public static final String USER_IN_CRASH_REPORT = "user_in_crash_report";
    }

    public class Splunk {
        public static final String SOLDIER = "bl_soldierid";
        public static final String PLATFORM = "bl_soldier_platform";
    }
}
