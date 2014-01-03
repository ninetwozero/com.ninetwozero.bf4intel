package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;

import java.util.List;

public class NewsItemAdapter extends BaseIntelAdapter<Object> {
    public NewsItemAdapter(final List<Object> items, final Context context) {
        super(items, context);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_news_comment, parent, false);
        }
        return convertView;
    }

}
