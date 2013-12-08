package com.ninetwozero.bf4intel.datatypes;

import com.ninetwozero.bf4intel.R;

public enum EventType {
    STATUSMESSAGE(R.layout.list_item_feed_status),
    BECAMEFRIENDS(R.layout.list_item_feed_friendship),
    CREATEDFORUMTHREAD(R.layout.list_item_feed_forum),
    WROTEFORUMPOST(R.layout.list_item_feed_forum),
    RECEIVEDWALLPOST(R.layout.list_item_feed_wallpost),
    ADDEDFAVSERVER(R.layout.list_item_feed_favorite_server),
    LEVELCOMPLETE(R.layout.list_item_feed_levelcomplete),
    GAMEREPORT(R.layout.list_item_feed_battlereport),
    COMMENTEDGAMEREPORT(R.layout.list_item_feed_gamecomment),
    COMMENTEDBLOG(R.layout.list_item_feed_blogcomment),
    GAMEACCESS(R.layout.list_item_feed_gameaccess),
    SHAREDGAMEEVENT(R.layout.list_item_feed_sharedgameevent),
    BATTLEPACK(R.layout.list_item_feed_battlepack),
    UNKNOWN(R.layout.list_item_feed_status);

    private final int layout;

    EventType(final int layout) {
        this.layout = layout;
    }

    public int getLayout() {
        return this.layout;
    }
}