package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

import java.util.List;

public class FeedAdapter extends BaseAdapter {

    private Context context;
    final LayoutInflater layoutInflater;
    private List<FeedItem> items;

    public FeedAdapter(final Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
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
    public FeedItem getItem(final int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final FeedItem item = getItem(position);
        final BaseEvent event = item.getEvent();

        if (convertView == null) {
            convertView = layoutInflater.inflate(item.getEvent().getEventType().getLayout(), parent, false);
        }

        populateGeneralViews(convertView, item);
        if (event.getEventType() == EventType.UNKNOWN) {
            populateUnknownEventView(convertView, item.getEventAsString());   
        } else {
            event.populateEventSpecificData(convertView);
        }
        return convertView;
    }

    private void populateUnknownEventView(final View convertView, final String eventAsString) {
        ((TextView) convertView.findViewById(R.id.content)).setText(
            String.format(
                context.getString(R.string.msg_unknown_event),
                eventAsString
            )
        );
    }

    private void populateGeneralViews(final View convertView, final FeedItem item) {
        ((TextView) convertView.findViewById(R.id.username)).setText(item.getOwner().getUsername());
        //((ImageView) convertView.findViewById(R.id.avatar)).setImageURI(UrlFactory.buildGravatarUrl(item.getOwner().getGravatarHash()));
        ((TextView) convertView.findViewById(R.id.category)).setText(fetchCategoryForType(item.getEvent().getEventType()));
        ((TextView) convertView.findViewById(R.id.timestamp)).setText(DateTimeUtils.toRelative(context, item.getDate()));
    }

    private int fetchCategoryForType(final EventType eventType) {
        switch (eventType) {
            case STATUSMESSAGE:
                return R.string.feed_category_status;
            case BECAMEFRIENDS:
                return R.string.feed_category_new_friend;
            case CREATEDFORUMTHREAD:
                return R.string.feed_category_forumthread;
            case WROTEFORUMPOST:
                return R.string.feed_category_forumpost;
            case RECEIVEDWALLPOST:
                return R.string.feed_category_wallpost;
            case ADDEDFAVSERVER:
                return R.string.feed_category_favoriteserver;
            case LEVELCOMPLETE:
                return R.string.feed_category_mission_completed;
            case GAMEREPORT:
                return R.string.feed_category_gamereport;
            case COMMENTEDGAMEREPORT:
                return R.string.feed_category_game_comment;
            case COMMENTEDBLOG:
                return R.string.feed_category_blog_comment;
            case GAMEACCESS:
                return R.string.feed_category_new_game;
            case SHAREDGAMEEVENT:
                return R.string.feed_category_unlocks;
            case BATTLEPACK:
                return R.string.feed_category_battlepack;
            default:
                return R.string.na;
        }
    }

    public void setItems(final List<FeedItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
