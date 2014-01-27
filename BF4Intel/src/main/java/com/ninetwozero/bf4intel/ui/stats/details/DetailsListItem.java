package com.ninetwozero.bf4intel.ui.stats.details;

import android.view.LayoutInflater;
import android.view.View;

import com.ninetwozero.bf4intel.base.adapter.BaseItem;

public class DetailsListItem implements BaseItem {

    private int resourceId;
    private String value;

    public DetailsListItem(int resourceId, String value) {
        this.resourceId = resourceId;
        this.value = value;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        return null;
    }
}
