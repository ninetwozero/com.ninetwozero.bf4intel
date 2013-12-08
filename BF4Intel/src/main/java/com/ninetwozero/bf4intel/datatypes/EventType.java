package com.ninetwozero.bf4intel.datatypes;

import com.ninetwozero.bf4intel.R;

public enum EventType {
    STATUS_MESSAGE(R.layout.list_item_feed_status),
    BECAME_FRIENDS(R.layout.list_item_feed_friendship),
    CREATED_FORUM_THREAD(R.layout.list_item_feed_forum),
    WROTE_FORUM_POST(R.layout.list_item_feed_forum),
    RECEIVED_WALL_POST(R.layout.list_item_feed_wallpost),
    ADDED_FAV_SERVER(R.layout.list_item_feed_favorite_server),
    LEVEL_COMPLETE(R.layout.list_item_feed_levelcomplete),
    GAME_REPORT(R.layout.list_item_feed_battlereport),
    COMMENTED_GAME_REPORT(R.layout.list_item_feed_gamecomment),
    COMMENTED_BLOG(R.layout.list_item_feed_blogcomment),
    GAME_ACCESS(R.layout.list_item_feed_gameaccess),
    SHARED_GAME_EVENT(R.layout.list_item_feed_sharedgameevent),
    BATTLE_PACK(R.layout.list_item_feed_battlepack),
    UNKNOWN(R.layout.list_item_feed_status);

    private final int layout;

    EventType(final int layout) {
        this.layout = layout;
    }

    public int getLayout() {
        return this.layout;
    }
}