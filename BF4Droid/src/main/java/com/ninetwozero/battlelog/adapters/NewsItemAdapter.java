package com.ninetwozero.battlelog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ninetwozero.battlelog.R;

import java.util.List;

/* TODO: This should extend AbstractAdapter when com.ninetwozero.common is available */

public class NewsItemAdapter extends BaseAdapter {

    private Context mContext;
    final LayoutInflater mInflater;
    private List<Object> mItems;


    public NewsItemAdapter(final Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    // TODO: Remove when hooked to website
    public NewsItemAdapter(final Context context, final List<Object> objects) {
        this(context);
        mItems = objects;
    }

    @Override
    public Object getItem(final int position) {
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
            convertView = mInflater.inflate(R.layout.list_item_news_comment, parent, false);
        }
        return convertView;
    }

}
