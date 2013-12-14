package com.ninetwozero.bf4intel.factories;

import org.apache.http.client.utils.URIUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlFactory {
    private static final String SCHEME = "http";
    private static final String HOST = "battlelog.battlefield.com/bf4";
    private static final int DEFAULT_PORT = -1;

    private static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/{HASH}/?s=320";

    public static String buildGravatarUrl(final String hash) {
        return GRAVATAR_URL.replace("{HASH}", hash);
    }

    public static URL soldierOverviewURL(int soldierId, int platformId) {
        return createURL(String.format("warsawoverviewpopulate/%d/%d/", soldierId, platformId));
    }

    public static URL weaponStatsURL(int soldierId, int platformId) {
        return createURL(String.format("warsawWeaponsPopulateStats/%d/%d/stats/", soldierId, platformId));
    }

    public static URL assignmentsURL(String soldierName, int soldierId, long userId, int platformId) {
        return createURL(String.format("soldier/missionsPopulateStats/%s/%d/%d/%d/"
            , soldierName, soldierId, userId, platformId));
    }

    public static URL awardsURL(int soldierId, int platformId) {
        return createURL(String.format("warsawawardspopulate/%d/%d/", soldierId, platformId));
    }

    public static URL battleReports(int soldierId, int platformId) {
        return createURL(String.format("warsawbattlereportspopulate/%d/2048/%d/", soldierId, platformId));
    }

    public static URL globalFeedURL(int indexFrom) {
        return createURL(String.format("feed/homeevents/?start=%d", indexFrom));
    }

    public static URL userFeedURL(long userId, int indexFrom) {
        return createURL(String.format("feed/profileevents/%d/?start=%d", userId, indexFrom));
    }

    private static URL createURL(String path) {
        return prepareURL(HOST, path, null);
    }

    private static URL prepareURL(String host, String path, String query) {
        try {
            return URIUtils.createURI(SCHEME, host, DEFAULT_PORT, path, query, null).toURL();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
