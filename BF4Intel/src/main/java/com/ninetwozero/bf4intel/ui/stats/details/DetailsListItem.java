package com.ninetwozero.bf4intel.ui.stats.details;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseItem;
import com.ninetwozero.bf4intel.base.adapter.BaseListAdapter;

public class DetailsListItem implements BaseItem {

    private int resourceId;
    private String value;

    public DetailsListItem(int resourceId, String value) {
        this.resourceId = resourceId;
        this.value = value;
    }

    @Override
    public int getViewType() {
        return BaseListAdapter.BaseItemType.ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_stats_details, null);
        }
        ((TextView) view.findViewById(R.id.score_label)).setText(resourceId);
        ((TextView) view.findViewById(R.id.score_value)).setText(value);
        return view;
    }
}
