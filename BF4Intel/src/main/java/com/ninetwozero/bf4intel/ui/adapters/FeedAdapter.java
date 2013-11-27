package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.jsonmodels.battlefeed.FeedItemElement;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.resourcemaps.FeedEventLayoutMap;

import java.util.List;

public class FeedAdapter extends BaseAdapter {

    private Context context;
    final LayoutInflater layoutInflater;
    private List<FeedItem> items;

    public FeedAdapter(final Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public FeedItem getItem(final int position) {
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
        final FeedItem item = getItem(position);
        final List<FeedItemElement> elements = item.toElementMap();
        if (convertView == null) {
            convertView = layoutInflater.inflate(FeedEventLayoutMap.get(item.getEvent().getEventName()), parent, false);
        }

        View tempView;
        Log.d("YOLO", "elements => " + elements);
        for (FeedItemElement element : elements) {
            tempView = convertView.findViewById(element.getResourceId());
            if (element.isImage()) {
                ((ImageView) tempView).setImageURI(Uri.parse(element.getContent()));
            } else {
                ((TextView) tempView).setText(element.getContent());
            }
        }
        return convertView;
    }

    public void setItems(final List<FeedItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
