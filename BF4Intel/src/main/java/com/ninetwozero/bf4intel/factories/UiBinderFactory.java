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
            put(EventType.ADDEDFAVSERVER, new FavoriteServerUiBinder());
            put(EventType.BATTLEPACK, new BattlePackUiBinder());
            put(EventType.BECAMEFRIENDS, new FriendshipUiBinder());
            put(EventType.COMMENTEDBLOG, new CommentedBlogUiBinder());
            put(EventType.COMMENTEDGAMEREPORT, new CommentedGameReportUiBinder());
            put(EventType.CREATEDFORUMTHREAD, new CreatedForumThreadUiBinder());
            put(EventType.GAMEACCESS, new GameAccessUiBinder());
            put(EventType.GAMEREPORT, new GameReportUiBinder());
            put(EventType.LEVELCOMPLETE, new CompletedLevelUiBinder());
            put(EventType.RECEIVEDWALLPOST, new WallpostUiBinder());
            put(EventType.STATUSMESSAGE, new StatusMessageUiBinder());
            put(EventType.SHAREDGAMEEVENT, new SharedGameEventUiBinder());
            put(EventType.UNKNOWN, new UnknownEventUiBinder());
            put(EventType.WROTEFORUMPOST, new ForumPostUiBinder());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T extends EventUiBinder> T get(final EventType eventType) {
        return (T) uiBinderMap.get(eventType);
    }
}
