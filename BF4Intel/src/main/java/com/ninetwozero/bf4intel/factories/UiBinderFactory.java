package com.ninetwozero.bf4intel.factories;

import com.ninetwozero.bf4intel.json.battlefeed.EventType;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.BattlePackLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.CommentedBlogLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.CommentedGameReportLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.CompletedLevelLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.CreatedForumThreadLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.FavoriteServerLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.ForumPostLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.FriendshipLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.GameAccessLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.GameReportLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.SharedGameEventLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.StatusMessageLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.UnknownEventLayout;
import com.ninetwozero.bf4intel.ui.battlefeed.layouts.WallpostLayout;

import java.util.HashMap;
import java.util.Map;

public class UiBinderFactory {
    private final static Map<EventType, EventLayout> uiBinderMap = new HashMap<EventType, EventLayout>() {
        {
            put(EventType.ADDED_FAV_SERVER, new FavoriteServerLayout());
            put(EventType.BATTLE_PACK, new BattlePackLayout());
            put(EventType.BECAME_FRIENDS, new FriendshipLayout());
            put(EventType.COMMENTED_BLOG, new CommentedBlogLayout());
            put(EventType.COMMENTED_GAME_REPORT, new CommentedGameReportLayout());
            put(EventType.CREATED_FORUM_THREAD, new CreatedForumThreadLayout());
            put(EventType.GAME_ACCESS, new GameAccessLayout());
            put(EventType.GAME_REPORT, new GameReportLayout());
            put(EventType.LEVEL_COMPLETE, new CompletedLevelLayout());
            put(EventType.RECEIVED_WALL_POST, new WallpostLayout());
            put(EventType.STATUS_MESSAGE, new StatusMessageLayout());
            put(EventType.SHARED_GAME_EVENT, new SharedGameEventLayout());
            put(EventType.UNKNOWN, new UnknownEventLayout());
            put(EventType.WROTE_FORUM_POST, new ForumPostLayout());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T extends EventLayout> T get(final EventType eventType) {
        return (T) uiBinderMap.get(eventType);
    }
}
