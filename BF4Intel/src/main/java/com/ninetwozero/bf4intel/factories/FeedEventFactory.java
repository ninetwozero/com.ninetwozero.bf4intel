package com.ninetwozero.bf4intel.factories;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.EventData;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.UnknownEvent;

import java.util.HashMap;
import java.util.Map;

import static com.ninetwozero.bf4intel.jsonmodels.battlefeed.EventData.EventType;

public class FeedEventFactory {
    private static final Map<String, EventType> EVENT_ENUM_MAP = new HashMap<String, EventType>() {{
        put("statusmessage", EventType.STATUSMESSAGE);
        put("becamefriends", EventType.BECAMEFRIENDS);
        put("createdforumthread", EventType.CREATEDFORUMTHREAD);
        put("wroteforumpost", EventType.WROTEFORUMPOST);
        put("receivedwallpost", EventType.RECEIVEDWALLPOST);
        put("addedfavserver", EventType.ADDEDFAVSERVER);
        put("rankedup", EventType.RANKEDUP);
        put("levelcomplete", EventType.LEVELCOMPLETE);
        put("gamereport", EventType.GAMEREPORT);
        put("receivedaward", EventType.RECEIVEDAWARD);
        put("assignmentcomplete", EventType.ASSIGNMENTCOMPLETE);
        put("commentedgamereport", EventType.COMMENTEDGAMEREPORT);
        put("commentedblog", EventType.COMMENTEDBLOG);
        put("gameaccess", EventType.GAMEACCESS);
        put("sharedgameevent", EventType.SHAREDGAMEEVENT);
    }};

    public static EventData create(final String event, final JsonObject jsonObject) {
        final EventType type = getType(event);
        switch (type) {
            case STATUSMESSAGE:
                return generateStatusMessageEvent(event, jsonObject);
            case BECAMEFRIENDS:
                return generateFriendshipEvent(event, jsonObject);
            case CREATEDFORUMTHREAD:
                return generateCreatedForumThreadEvent(event, jsonObject);
            case WROTEFORUMPOST:
                return generateWroteForumPostEvent(event, jsonObject);
            case RECEIVEDWALLPOST:
                return generateReceivedWallPostEvent(event, jsonObject);
            case ADDEDFAVSERVER:
                return generateFavoriteServerEvent(event, jsonObject);
            case RANKEDUP:
                return generateRankUpEvent(event, jsonObject);
            case LEVELCOMPLETE:
                return generateLevelCompletedEvent(event, jsonObject);
            case GAMEREPORT:
                return generateGameReportEvent(event, jsonObject);
            case RECEIVEDAWARD:
                return generateAwardReceivedEvent(event, jsonObject);
            case ASSIGNMENTCOMPLETE:
                return generateAssignmentCompletionEvent(event, jsonObject);
            case COMMENTEDGAMEREPORT:
                return generateGameReportCommentEvent(event, jsonObject);
            case COMMENTEDBLOG:
                return generateBlogCommentEvent(event, jsonObject);
            case GAMEACCESS:
                return generateGameAccessEvent(event, jsonObject);
            case SHAREDGAMEEVENT:
                return generateSharedGameEvent(event, jsonObject);
            default:
                return new UnknownEvent(event);
        }
    }

    private static EventType getType(final String event) {
        return EVENT_ENUM_MAP.containsKey(event)? EVENT_ENUM_MAP.get(event) : EventType.UNKNOWN;
    }

    /* TODO: All of this horrible shit */

    private static EventData generateStatusMessageEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateFriendshipEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateCreatedForumThreadEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateWroteForumPostEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateReceivedWallPostEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateFavoriteServerEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateRankUpEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateLevelCompletedEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateGameReportEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateAwardReceivedEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateAssignmentCompletionEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateGameReportCommentEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateBlogCommentEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateGameAccessEvent(final String event, final JsonObject jsonObject) {
        return null;
    }

    private static EventData generateSharedGameEvent(final String event, final JsonObject jsonObject) {
        return null;
    }
}
