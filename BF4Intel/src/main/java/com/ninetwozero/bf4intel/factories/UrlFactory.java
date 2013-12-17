package com.ninetwozero.bf4intel.factories;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.utils.URIUtils;

public class UrlFactory {
    private static final String SCHEME = "http";
    private static final String HOST = "battlelog.battlefield.com/bf4";
    private static final int DEFAULT_PORT = -1;

    private static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/%s/?s=320&d=%s";
    private static final String DEFAULT_GRAVATAR = "http://battlelog-cdn.battlefield.com/cdnprefix/avatar1/public/base/shared/default-avatar-320.png";

    public static String buildGravatarUrl(final String hash) {
        return String.format(GRAVATAR_URL, hash, DEFAULT_GRAVATAR);
    }

    public static URL buildSoldierOverviewURL(final int soldierId, final int platformId) {
        return createURL(String.format("warsawoverviewpopulate/%d/%d/", soldierId, platformId));
    }

    public static URL buildWeaponStatsURL(final int soldierId, final int platformId) {
        return createURL(String.format("warsawWeaponsPopulateStats/%d/%d/stats/", soldierId, platformId));
    }

    public static URL buildVehicleStatsURL(final int soldierId, final int platformId) {
        return createURL(String.format("warsawvehiclesPopulateStats/%d/%d/stats/", soldierId, platformId));
    }

    public static URL buildAssignmentsURL(final String soldierName, final int soldierId, final long userId, final int platformId) {
        return createURL(
            String.format(
                "soldier/missionsPopulateStats/%s/%d/%d/%d/",
                soldierName,
                soldierId,
                userId,
                platformId
            )
        );
    }

    public static URL buildAwardsURL(final int soldierId, final int platformId) {
        return createURL(String.format("warsawawardspopulate/%d/%d/", soldierId, platformId));
    }

    public static URL buildBattleReportsURL(final int soldierId, final int platformId) {
        return createURL(String.format("warsawbattlereportspopulate/%d/2048/%d/", soldierId, platformId));
    }

    public static URL buildGlobalFeedURL(final int offset) {
        return createURL(String.format("feed/homeevents/?start=%d", offset));
    }

    public static URL buildUserFeedURL(final long userId, final int offset) {
        return createURL(String.format("feed/profileevents/%d/?start=%d", userId, offset));
    }

    public static URL buildUserSearchURL() {
        return createURL("search/query/");
    }

    private static URL createURL(final String path) {
        return prepareURL(HOST, path, null);
    }

    private static URL prepareURL(final String host, final String path, final String query) {
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
