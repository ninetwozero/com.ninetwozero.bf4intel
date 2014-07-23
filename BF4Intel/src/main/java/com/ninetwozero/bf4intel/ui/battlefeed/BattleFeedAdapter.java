package com.ninetwozero.bf4intel.ui.battlefeed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.factories.FeedEventLayoutFactory;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.resources.maps.FeedEventLayoutMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

public class BattleFeedAdapter extends BaseIntelAdapter<FeedItem> {

    public BattleFeedAdapter(final Context context) {
        super(context);
    }

    @Override
    public int getViewTypeCount() {
        return EventType.values().length;
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getEvent().getEventType().ordinal();
    }

    @Override
    public long getItemId(final int position) {
        return Long.parseLong(getItem(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final FeedItem item = getItem(position);
        final BaseEvent event = item.getEvent();

        if (convertView == null) {
            convertView = layoutInflater.inflate(
                FeedEventLayoutMap.get(item.getEvent().getEventType()),
                parent,
                false
            );
        }

        populateGeneralViews(convertView, item);
        if (event.getEventType() == EventType.UNKNOWN) {
            populateUnknownEventView(convertView, item.getEventAsString());   
        } else {
          FeedEventLayoutFactory.get(event.getEventType()).populateView(context, convertView, event);
        }
        return convertView;
    }

    private void populateUnknownEventView(final View convertView, final String eventAsString) {
        setText(convertView, R.id.content, String.format(context.getString(R.string.msg_unknown_event), eventAsString));
    }

    private void populateGeneralViews(final View convertView, final FeedItem item) {
        loadImage(convertView, R.id.avatar, UrlFactory.buildGravatarUrl(item.getOwner().getGravatarHash()));
        setText(convertView, R.id.username, item.getOwner().getUsername());
        setText(convertView, R.id.category, fetchCategoryForType(item.getEvent().getEventType()));
        setText(convertView, R.id.timestamp, DateTimeUtils.toRelative(item.getDate()));
    }

    private int fetchCategoryForType(final EventType eventType) {
        switch (eventType) {
            case STATUS_MESSAGE:
                return R.string.feed_category_status;
            case BECAME_FRIENDS:
                return R.string.feed_category_new_friend;
            case CREATED_FORUM_THREAD:
                return R.string.feed_category_forumthread;
            case WROTE_FORUM_POST:
                return R.string.feed_category_forumpost;
            case RECEIVED_WALL_POST:
                return R.string.feed_category_wallpost;
            case ADDED_FAV_SERVER:
                return R.string.feed_category_favoriteserver;
            case LEVEL_COMPLETE:
                return R.string.feed_category_mission_completed;
            case GAME_REPORT:
                return R.string.feed_category_gamereport;
            case COMMENTED_GAME_REPORT:
                return R.string.feed_category_game_comment;
            case COMMENTED_BLOG:
                return R.string.feed_category_blog_comment;
            case GAME_ACCESS:
                return R.string.feed_category_new_game;
            case SHARED_GAME_EVENT:
                return R.string.feed_category_unlocks;
            case BATTLE_PACK:
                return R.string.feed_category_battlepack;
            case RANKED_UP:
                return R.string.feed_category_ranked_up;
            default:
                return R.string.na;
        }
    }
}
