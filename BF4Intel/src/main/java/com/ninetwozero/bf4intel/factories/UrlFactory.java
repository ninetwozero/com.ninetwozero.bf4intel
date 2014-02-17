package com.ninetwozero.bf4intel.factories;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

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

    public static URL buildSoldierListURL(final String soldierId) {
        return createURL(String.format("user/overviewBoxStats/%s/", soldierId));
    }

    public static URL buildSoldierOverviewURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawoverviewpopulate/%d/%d/", soldierId, platformId));
    }

    public static URL buildWeaponStatsURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawWeaponsPopulateStats/%d/%d/stats/", soldierId, platformId));
    }

    public static URL buildVehicleStatsURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawvehiclesPopulateStats/%d/%d/stats/", soldierId, platformId));
    }

    public static URL buildBattleReportsURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawbattlereportspopulate/%d/2048/%d/", soldierId, platformId));
    }

    public static URL buildReportMoreStatsURL(final long soldierId, final int platformId, final long reportId) {
        return createURL(String.format(Locale.getDefault(), "warsawbattlereportspopulate/%d/2048/%d/%d", soldierId, platformId, reportId));
    }

    public static URL buildDetailsURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawdetailedstatspopulate/%d/%d/", soldierId, platformId));
    }

    public static URL buildAssignmentsURL(final String soldierName, final long soldierId, final long userId, final int platformId) {
        return createURL(
            String.format(
                Locale.getDefault(),
                "soldier/missionsPopulateStats/%s/%d/%d/%d/",
                soldierName,
                soldierId,
                userId,
                platformId
            )
        );
    }

    public static URL buildAwardsURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawawardspopulate/%d/%d/", soldierId, platformId));
    }

    public static URL buildGlobalFeedURL(final int offset) {
        return createURL(String.format(Locale.getDefault(), "feed/homeevents/?start=%d", offset));
    }

    public static URL buildUserFeedURL(final long userId, final int offset) {
        return createURL(String.format(Locale.getDefault(), "feed/profileevents/%d/?start=%d", userId, offset));
    }

    public static URL buildUserSearchURL() {
        return createURL("search/query/");
    }

    public static URL buildWeaponUnlocksURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawWeaponsPopulateStats/%d/%d/unlocks/", soldierId, platformId));
    }

    public static URL buildVehicleUnlocksURL(final long soldierId, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawvehiclesPopulateStats/%d/%d/unlocks/", soldierId, platformId));
    }

    public static URL buildKitUnlocksURL(final long soldierId, final String soldierName, final int platformId) {
        return createURL(String.format(Locale.getDefault(), "warsawkitspopulatestats/%d/%d/", soldierId, platformId));
    }

    public static URL buildDogtagsURL(
        final String soldierId, final String soldierName, final int platformId, final int page
    ) {
        return createURL(
            String.format(
                Locale.getDefault(),
                "soldier/dogtagsPopulateStats/%s/%s/%d/%d/",
                soldierName,
                soldierId,
                platformId,
                page
            )
        );
    }

    public static URL buildWeaponAccessoriesURL(final String soldierId, final String guid, final int platformId) {
        // See: http://battlelog.battlefield.com/bf4/warsawWeaponAccessoriesPopulateStats/177958806/2/386F9329-7DE7-6FB9-1366-2877C698D9B7/
        return createURL(String.format(Locale.getDefault(), "warsawWeaponAccessoriesPopulateStats/%s/%d/%s/", soldierId, platformId, guid));
    }

    public static URL buildNewsListingURL(final int pageId) {
        return createURL(String.format(Locale.getDefault(), "news/%d/", pageId));
    }

    public static URL buildNewsArticleURL(final String articleId) {
        // The 1 in the URL indicates which comment page to start on
        return createURL(
            String.format(Locale.getDefault(), "news/view/%s/1/", articleId)
        );
    }
    public static URL buildNewsArticleWebURL(final String slug) {
        return createURL(String.format(Locale.getDefault(), "news/view/%s/", slug));
    }

    public static URL buildNewsArticleCommentsURL(final String articleId, final int pageId) {
        return createURL(String.format(Locale.getDefault(), "news/view/%s/%d/", articleId, pageId));
    }

    public static URL buildNewsArticleCommentUpvoteURL(final String commentId) {
        return createURL(String.format(Locale.getDefault(), "comment/upvote/%s/", commentId));
    }

    public static URL buildNewsArticleCommentDownvoteURL(final String commentId) {
        return createURL(String.format(Locale.getDefault(), "comment/removevote/%s/", commentId));
    }

    public static URL buildNewsArticleCommentRepliesURL(final String articleId, final int offset, final int pageId) {
        return createURL(
            String.format(Locale.getDefault(), "comment/getreplies/%s/%d/%d/", articleId, offset, pageId)
        );
    }

    public static URL buildNewsArticlePostCommentURL(final String articleId) {
        return createURL(String.format(Locale.getDefault(), "comment/postcomment/%s/devblog-comment/", articleId));
    }

    public static URL buildNewsArticlePostCommentReplyURL(final String articleId, final String commentId) {
        return createURL(String.format(Locale.getDefault(), "comment/postreply/%s/%s/", commentId, articleId));
    }

    public static URL buildNewsArticleHooahURL(final String articleId) {
        return createURL(String.format(Locale.getDefault(), "news/togglevote/%s/", articleId));
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
