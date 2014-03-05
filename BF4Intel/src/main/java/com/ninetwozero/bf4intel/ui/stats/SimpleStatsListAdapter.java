package com.ninetwozero.bf4intel.ui.stats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class SimpleStatsListAdapter extends ArrayAdapter<BaseStatsItem> {
    final LayoutInflater layoutInflater;

    public enum StatsRowType {
        ITEM, HEADER;
    }

    public SimpleStatsListAdapter(final Context context, List<BaseStatsItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return StatsRowType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        return getItem(position).getView(layoutInflater, view);
    }
}
