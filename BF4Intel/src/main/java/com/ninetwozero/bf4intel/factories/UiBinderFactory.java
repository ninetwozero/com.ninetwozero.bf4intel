package com.ninetwozero.bf4intel.factories;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.BattlePackUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.CommentedBlogUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.CommentedGameReportUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.CompletedLevelUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.CreatedForumThreadUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.FavoriteServerUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.ForumPostUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.FriendshipUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.GameAccessUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.GameReportUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.SharedGameEventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.StatusMessageUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.UnknownEventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.uibinders.WallpostUiBinder;

import java.util.HashMap;
import java.util.Map;

public class UiBinderFactory {
    private final static Map<EventType, EventUiBinder> uiBinderMap = new HashMap<EventType, EventUiBinder>() {
        {
            put(EventType.ADDED_FAV_SERVER, new FavoriteServerUiBinder());
            put(EventType.BATTLE_PACK, new BattlePackUiBinder());
            put(EventType.BECAME_FRIENDS, new FriendshipUiBinder());
            put(EventType.COMMENTED_BLOG, new CommentedBlogUiBinder());
            put(EventType.COMMENTED_GAME_REPORT, new CommentedGameReportUiBinder());
            put(EventType.CREATED_FORUM_THREAD, new CreatedForumThreadUiBinder());
            put(EventType.GAME_ACCESS, new GameAccessUiBinder());
            put(EventType.GAME_REPORT, new GameReportUiBinder());
            put(EventType.LEVEL_COMPLETE, new CompletedLevelUiBinder());
            put(EventType.RECEIVED_WALL_POST, new WallpostUiBinder());
            put(EventType.STATUS_MESSAGE, new StatusMessageUiBinder());
            put(EventType.SHARED_GAME_EVENT, new SharedGameEventUiBinder());
            put(EventType.UNKNOWN, new UnknownEventUiBinder());
            put(EventType.WROTE_FORUM_POST, new ForumPostUiBinder());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T extends EventUiBinder> T get(final EventType eventType) {
        return (T) uiBinderMap.get(eventType);
    }
}
