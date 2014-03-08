package com.ninetwozero.bf4intel.model.stats.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;

import java.util.List;

public class DetailStatsGroup implements BaseListItem {
    private List<BaseListItem> items;

    public DetailStatsGroup(final List<BaseListItem> items) {
        this.items = items;
    }

    @Override
    public int getViewType() {
        return SimpleListAdapter.RowType.GROUP.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_stats_details, null, false);
        } else {
            ((ViewGroup) convertView).removeAllViews();
        }

        for (BaseListItem item : items) {
            ((ViewGroup) convertView).addView(item.getView(inflater, null));
        }
        return convertView;
    }
}
