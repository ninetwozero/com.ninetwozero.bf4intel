package com.ninetwozero.bf4intel.ui.battlefeed;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
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
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleFeedAdapter extends BaseAdapter {
    private Map<EventType, EventUiBinder<? extends BaseEvent>> uiBinderMap = new HashMap<EventType, EventUiBinder<? extends BaseEvent>>() {
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

    private Context context;
    final LayoutInflater layoutInflater;
    private List<FeedItem> items;

    public BattleFeedAdapter(final Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);
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
            Log.d("YOLO", "event => " + event);
            Log.d("YOLO", "event.getEventType() => " + event.getEventType());
            Log.d("YOLO", "uiBinderMap => " + uiBinderMap.get(event.getEventType()));
            // FIXME: uiBinderMap.get(event.getEventType())).populateView(context, convertView, event);
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
