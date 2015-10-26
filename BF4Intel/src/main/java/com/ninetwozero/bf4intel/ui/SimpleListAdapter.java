package com.ninetwozero.bf4intel.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class SimpleListAdapter extends ArrayAdapter<BaseListItem> {
    final LayoutInflater layoutInflater;

    public enum RowType {
        GROUP, ITEM, HEADER
    }

    public SimpleListAdapter(final Context context, List<BaseListItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return RowType.values().length;
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
