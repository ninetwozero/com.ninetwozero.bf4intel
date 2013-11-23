package com.ninetwozero.bf4intel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ninetwozero.bf4intel.R;

import java.util.List;

/* TODO: This should extend AbstractAdapter when com.ninetwozero.common is available */

public class NewsItemAdapter extends BaseAdapter {

    private Context context;
    final LayoutInflater layoutInflater;
    private List<Object> items;


    public NewsItemAdapter(final Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    // TODO: Remove when hooked to website
    public NewsItemAdapter(final Context context, final List<Object> objects) {
        this(context);
        items = objects;
    }

    @Override
    public Object getItem(final int position) {
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
            convertView = layoutInflater.inflate(R.layout.list_item_news_comment, parent, false);
        }
        return convertView;
    }

}
