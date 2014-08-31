package com.ninetwozero.bf4intel.ui.stats.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsGroup;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsItem;
import com.ninetwozero.bf4intel.resources.maps.DetailedStatsTitleMap;

import java.util.HashMap;
import java.util.Map;


public class DetailedStatsAdapter extends BaseIntelAdapter<DetailedStatsGroup> {
    private Map<String, Integer> headerMap = new HashMap<String, Integer>() {
        {
            put(DetailedStatsGroup.MULTIPLAYER_SCORES, R.string.multiplayer_score);
            put(DetailedStatsGroup.GENERAL_SCORES, R.string.general_score);
            put(DetailedStatsGroup.GAME_MODE_SCORES, R.string.game_modes);
            put(DetailedStatsGroup.TEAM_SCORES, R.string.team_score);
            put(DetailedStatsGroup.EXTRA_SCORES, R.string.extra_score);
            put(DetailedStatsGroup.GAME_MODE_EXTRA_SCORES, R.string.game_mode_extra);
        }
    };

    public DetailedStatsAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final DetailedStatsGroup group = getItem(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_stats_details, parent, false);
        }

        final ViewGroup cardRoot = (ViewGroup) view.findViewById(R.id.card_root);
        cardRoot.removeAllViews();

        setText(view, R.id.title, fetchTitleForGroup(group.getKey()));
        for (DetailedStatsItem statsItem : group.getStats()) {
            final View tempView = layoutInflater.inflate(R.layout.list_item_stats_details_row, cardRoot, false);
            setText(tempView, R.id.score_label, DetailedStatsTitleMap.get(statsItem.getKey()));
            setText(tempView, R.id.score_value, statsItem.getValue());
            cardRoot.addView(tempView);
        }
        return view;
    }

    private int fetchTitleForGroup(final String key) {
        return headerMap.containsKey(key) ? headerMap.get(key) : R.string.na;
    }
}
