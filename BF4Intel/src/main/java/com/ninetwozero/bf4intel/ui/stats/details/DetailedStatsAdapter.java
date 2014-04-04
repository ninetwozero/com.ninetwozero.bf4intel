package com.ninetwozero.bf4intel.ui.stats.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.json.StickyHeaderItem;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsItem;
import com.ninetwozero.bf4intel.resources.maps.DetailedStatsTitleMap;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public class DetailedStatsAdapter
    extends BaseIntelAdapter<List<DetailedStatsItem>>
    implements StickyListHeadersAdapter {

    private List<StickyHeaderItem> headers;

    public DetailedStatsAdapter(final Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final List<DetailedStatsItem> statsGroup = itemsList.get(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_stats_details, parent, false);
        } else {
            ((ViewGroup) view).removeAllViews();
        }

        for (DetailedStatsItem statsItem : statsGroup) {
            final View tempView = layoutInflater.inflate(R.layout.list_item_stats_details_row, null);
            setText(tempView, R.id.score_label, DetailedStatsTitleMap.get(statsItem.getKey()));
            setText(tempView, R.id.score_value, statsItem.getValue());
            ((ViewGroup) view).addView(tempView);
        }

        return view;
    }

    @Override
    public View getHeaderView(int position, View view, ViewGroup viewGroup) {
        final StickyHeaderItem header = getHeader(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_header_details, viewGroup, false);
        }

        setText(view, R.id.list_header, header.getTitle());
        return view;
    }

    private StickyHeaderItem getHeader(int position) {
        return headers.get(position);
    }

    @Override
    public long getHeaderId(int i) {
        return i;
    }

    public void setHeaders(List<StickyHeaderItem> headers) {
        this.headers = headers;
    }
}
