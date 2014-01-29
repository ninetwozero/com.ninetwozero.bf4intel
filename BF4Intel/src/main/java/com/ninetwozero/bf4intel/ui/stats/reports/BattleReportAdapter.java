package com.ninetwozero.bf4intel.ui.stats.reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ninetwozero.bf4intel.base.adapter.BaseItem;

import java.util.List;

public class BattleReportAdapter extends ArrayAdapter<BaseItem> {
    final LayoutInflater layoutInflater;

    public enum BattleReportType {
        ITEM, HEADER;
    }

    public BattleReportAdapter(final Context context, List<BaseItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return BattleReportType.values().length;
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
