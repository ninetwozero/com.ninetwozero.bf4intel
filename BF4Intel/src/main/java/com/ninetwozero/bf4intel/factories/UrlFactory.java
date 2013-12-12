package com.ninetwozero.bf4intel.factories;

public class UrlFactory {
    private static final String SCHEME = "http";
    private static final String HOST = "battlelog.battlefield.com/bf4";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final int DEFAULT_PORT = -1;


    private static final String BASE_URL = "http://battlelog.battlefield.com/bf4/";
    private static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/{HASH}/?s=320";

    public enum Type {
        BATTLE_REPORT_LISTING("warsawbattlereportspopulate/{SOLDIER_ID}/2048/{PLATFORM}/"),
        SOLDIER_OVERVIEW("warsawoverviewpopulate/{SOLDIER_ID}/{PLATFORM}/"),
        ASSIGNMENTS("soldier/missionsPopulateStats/LittleBoySVK/{SOLDIER_ID}/{USER_ID}/{PLATFORM}/"),

        GLOBAL_FEED("feed/homeevents/?start={COUNT}"),
        USER_FEED("feed/profileevents/{PROFILE_ID}/?start={COUNT}");

        private String url;
        Type(final String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }

    public static String build(final Type type, final Object... data) {
        String urlString = BASE_URL + type.getUrl();
        for (Object value : data) {
            urlString = urlString.replaceFirst("\\{\\w+\\}", String.valueOf(value));
        }
        return urlString;
    }

    public static String buildGravatarUrl(final String hash) {
        return GRAVATAR_URL.replace("{HASH}", hash);
    }
}
