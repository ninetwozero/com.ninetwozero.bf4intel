package com.ninetwozero.bf4intel.ui.stats;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;

public class SimpleStatsHeader implements BaseStatsItem {

    private final int headerResource;

    public SimpleStatsHeader(int headerResource) {
        this.headerResource = headerResource;
    }

    @Override
    public int getViewType() {
        return SimpleStatsListAdapter.StatsRowType.HEADER.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_header, null);
        }

        ((TextView) view.findViewById(R.id.list_header)).setText(headerResource);
        return view;
    }
}
