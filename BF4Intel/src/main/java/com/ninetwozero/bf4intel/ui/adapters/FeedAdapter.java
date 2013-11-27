package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class FeedAdapter extends BaseAdapter {

    private Context context;
    final LayoutInflater layoutInflater;
    private List<Integer> items;

    public FeedAdapter(final Context context, final List<Integer> objects) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
        items = objects;
    }

    @Override
    public Integer getItem(final int position) {
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
        if (convertView == null) {
            convertView = layoutInflater.inflate(getItem(position), parent, false);
        }
        return convertView;
    }

}
