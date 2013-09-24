package com.ninetwozero.battlelog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class FeedAdapter extends BaseAdapter {

    private Context mContext;
    final LayoutInflater mInflater;
    private List<Integer> mItems;

    public FeedAdapter(final Context context, final List<Integer> objects) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mItems = objects;
    }

    @Override
    public Integer getItem(final int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(getItem(position), parent, false);
        }
        return convertView;
    }

}
