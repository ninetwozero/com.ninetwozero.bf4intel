package com.ninetwozero.bf4intel.network;

import java.net.URL;
import java.util.Locale;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Bf4IntelService {
     static final String HOST = "http://battlelog.battlefield.com/bf4";
    static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/%s/?s=320&d=%s";

    static final String CDN_HOST = "http://battlelog-cdn.battlefield.com";
    static final String DEFAULT_GRAVATAR = CDN_HOST + "/cdnprefix/avatar1/public/base/shared/default-avatar-320.png";

    /*
    TODO fix gravatar
    public static String buildGravatarUrl(final String hash) {
        return String.format(GRAVATAR_URL, hash, DEFAULT_GRAVATAR);
    }*/

    @GET("/bf4/user/overviewBoxStats/{soldierId}/")
    void buildSoldierListURL(@Path("soldierId") String soldierId);

    @GET("/bf4/warsawoverviewpopulate/{soldierId}/{platformId}/")
    void buildSoldierOverviewURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/warsawWeaponsPopulateStats/{soldierId}/{platformId}/stats/")
    void buildWeaponStatsURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/warsawvehiclesPopulateStats/{soldierId}/{platformId}/stats/")
    void buildVehicleStatsURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/warsawbattlereportspopulate/{soldierId}/2048/{platformId}/")
    void buildBattleReportsURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/warsawbattlereportspopulate/{soldierId}/2048/{platformId}/{reportId}")
    void buildReportMoreStatsURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId, @Path("reportId") long reportId);

    @GET("/bf4/warsawdetailedstatspopulate/{soldierId}/{platformId}/")
    void buildDetailsURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/soldier/missionsPopulateStats/{soldierName}/{soldierId}/{userId}/{platformId}/")
    /*TODO find some soldier with space in name to see if this workssoldierName.replace(" ", "%20"),*/
    void buildAssignmentsURL(@Path("soldierName") String soldierName, @Path("soldierId") String soldierId, @Path("userId") String userId, @Path("platformId") int platformId);

    @GET("/bf4/warsawawardspopulate/{soldierId}/{platformId}/")
    void buildAwardsURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/feed/homeevents/?start={offset}")
    void buildGlobalFeedURL(@Path("offset") int offset);

    @GET("/bf4/feed/profileevents/{userId}/?start={offset}")
    void buildUserFeedURL(@Path("userId") String userId, final int offset);

    @FormUrlEncoded
    @POST("/bf4/search/query/")
    void buildUserSearchURL(@Field("query") String query);

    @GET("/bf4/warsawWeaponsPopulateStats/{soldierId}/{platformId}/unlocks/")
    void buildWeaponUnlocksURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/warsawvehiclesPopulateStats/{soldierId}/{platformId}/unlocks/")
    void buildVehicleUnlocksURL(@Path("soldierId") String soldierId, @Path("platformId") int platformId);

    @GET("/bf4/warsawkitspopulatestats/{soldierId}/{platformId}/")
    void buildKitUnlocksURL(@Path("soldierId") String soldierId, final String soldierName, @Path("platformId") int platformId);

    @Deprecated
    @GET("/bf4/soldier/dogtagsPopulateStats/{soldierId}/{soldierName}/{platformId}/{page}/")
    /*soldierName.replace(" ", "%20"),*/
    void buildDogtagsURL(@Path("soldierId") final String soldierId, @Path("soldierName") String soldierName, @Path("platformId") int platformId, @Path("page") int page);

    @Deprecated
    @GET("/bf4//cdnprefix/avatar1/public/profile/warsaw/gamedata/dogtags/large/{imageFormat}")
    void buildDogtagImageURL(int imageIndex, boolean advanced);
        /*if (advanced) {
            filename = "advanced" + imageIndex + ".png";
        } else {
            filename = "basic" + imageIndex + ".png";
        }*/

    @Deprecated
    @GET("/bf4/warsawWeaponAccessoriesPopulateStats/{soldierId}/{guid}/{platformId}/")
        // See: http://battlelog.battlefield.com/bf4/warsawWeaponAccessoriesPopulateStats/177958806/2/386F9329-7DE7-6FB9-1366-2877C698D9B7/
    void buildWeaponAccessoriesURL(@Path("soldierId") String soldierId, @Path("guid") String guid, @Path("platformId") int platformId);

    @GET("/bf4/news/{pageId}/")
    void buildNewsListingURL(@Path("pageId") int pageId);

    // The 1 in the URL indicates which comment page to start on
    @GET("/bf4/news/view/%s/1/")
    void buildNewsArticleURL(@Path("articleId") String articleId);

    @GET("/bf4/news/view/{slug}/")
    void buildNewsArticleWebURL(@Path("slug") String slug);

    @GET("/bf4/news/view/{articleId}/{pageId}/")
    void buildNewsArticleCommentsURL(@Path("articleId") String articleId, @Path("pageId") int pageId);

    @GET("/bf4/comment/upvote/{commentId}/")
    void buildNewsArticleCommentUpvoteURL(@Path("commentId") String commentId);

    @GET("/bf4/comment/removevote/{commentId}/")
    void buildNewsArticleCommentDownvoteURL(@Path("commentId") String commentId);

    @GET("/bf4/comment/getreplies/{articleId}/{offset}/{pageId}/")
    void buildNewsArticleCommentRepliesURL(@Path("articleId") String articleId, @Path("offset") int offset, @Path("pageId") int pageId);

    @GET("/bf4/comment/postcomment/{articleId}/devblog-comment/")
    void buildNewsArticlePostCommentURL(@Path("articleId") String articleId);

    @GET("/bf4/comment/postreply/{articleId}/{commentId}/")
    void buildNewsArticlePostCommentReplyURL(@Path("articleId") String articleId, @Path("commentId") String commentId);

    @GET("/bf4/news/togglevote/{articleId}/")
    void buildNewsArticleHooahURL(@Path("articleId") String articleId);

    /*private static URL createURL(final String path) {
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
    }*/
}
