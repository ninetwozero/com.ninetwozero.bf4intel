package com.ninetwozero.bf4intel.base.factories;

public class UrlFactory {
    private static final String BASE_URL = "http://battlelog.battlefield.com/bf4/";

    public enum Type {
        BATTLE_REPORT_LISTING("warsawbattlereportspopulate/{SOLDIER_ID}/2048/{PLATFORM}/"),
        SOLDIER_OVERVIEW("warsawoverviewpopulate/{SOLDIER_ID}/{PLATFORM}/"),
        ASSIGNMENTS("soldier/missionsPopulateStats/LittleBoySVK/{SOLDIER_ID}/{USER_ID}/{PLATFORM}/");

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
}
