package com.ninetwozero.bf4intel.datatypes;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.BattlePackEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.CommentedBlogEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.CommentedGameReportEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.CompletedLevelEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.CreatedForumThreadEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.FavoriteServerEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.ForumPostEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.FriendshipEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.GameAccessEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.GameReportEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.SharedGameEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.StatusMessageEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.UnknownEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.WallpostEvent;

public enum EventType {
    STATUSMESSAGE(StatusMessageEvent.class, R.layout.list_item_feed_status),
    BECAMEFRIENDS(FriendshipEvent.class, R.layout.list_item_feed_friendship),
    CREATEDFORUMTHREAD(CreatedForumThreadEvent.class, R.layout.list_item_feed_forum),
    WROTEFORUMPOST(ForumPostEvent.class, R.layout.list_item_feed_forum),
    RECEIVEDWALLPOST(WallpostEvent.class, R.layout.list_item_feed_wallpost),
    ADDEDFAVSERVER(FavoriteServerEvent.class, R.layout.list_item_feed_favorite_server),
    LEVELCOMPLETE(CompletedLevelEvent.class, R.layout.list_item_feed_levelcomplete),
    GAMEREPORT(GameReportEvent.class, R.layout.list_item_feed_battlereport),
    COMMENTEDGAMEREPORT(CommentedGameReportEvent.class, R.layout.list_item_feed_gamecomment),
    COMMENTEDBLOG(CommentedBlogEvent.class, R.layout.list_item_feed_blogcomment),
    GAMEACCESS(GameAccessEvent.class, R.layout.list_item_feed_gameaccess),
    SHAREDGAMEEVENT(SharedGameEvent.class, R.layout.list_item_feed_status), // TODO
    BATTLEPACK(BattlePackEvent.class, R.layout.list_item_feed_status), // TODO
    UNKNOWN(UnknownEvent.class, R.layout.list_item_feed_status);

    private final Class<? extends BaseEvent> eventClass;
    private final int layout;

    <T extends BaseEvent> EventType(final Class<T> classType, final int layout) {
        this.eventClass = classType;
        this.layout = layout;
    }

    public Class<? extends BaseEvent> getEventClass() {
        return this.eventClass;
    }
    public int getLayout() {
        return this.layout;
    }
}