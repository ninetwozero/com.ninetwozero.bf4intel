package com.ninetwozero.bf4intel.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.ListRow;
import com.ninetwozero.bf4intel.datatypes.ListRowType;

import java.io.File;
import java.util.List;

import static com.ninetwozero.bf4intel.datatypes.ListRowType.*;

/* TODO:
    Implement the different view types so that the adapter can recycle accordingly
 */

public class ListRowAdapter extends BaseAdapter {
    private Context mContext;
    private List<ListRow> mItems;

    public ListRowAdapter(final Context context, final List<ListRow> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getViewTypeCount() {
        return ListRowType.SIZE;
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getType().ordinal();
    }

    @Override
    public ListRow getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(final int position) {
        return getItem(position).getType().isEnabled();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ListRow item = mItems.get(position);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(item.getLayout(), viewGroup, false);
        }
        return populateViewFromItem(view, item);
    }

    /*
        TODO:
        Inherit AbstractListAdapter from com.ninetwozero.common (release pending)
        These two methods need to be removed when this dependency is in place
    */
    private void setText(final View parent, final int resourceId, final String text) {
        ((TextView) parent.findViewById(resourceId)).setText(text);
    }

    private void setText(final View parent, final int resourceId, final int textResourceId) {
        ((TextView) parent.findViewById(resourceId)).setText(textResourceId);
    }
    /* END OF TODO */

    private View populateViewFromItem(final View view, final ListRow item) {
        final Bundle stringMappings = item.getStringMappings();
        final Bundle drawableMappings = item.getDrawableMappings();
        final ListRowType type = item.getType();
        final boolean isRegular = type == SIDE_REGULAR || type == SIDE_REGULAR_CHILD;
        final boolean isHeading = type == HEADING || type == SIDE_HEADING;

        if (isRegular || isHeading) {
            setText(view, R.id.text1, item.getTitle());
        } else {
            populateTextViews(view, stringMappings);
            populateImageViews(view, drawableMappings);
        }
        return view;
    }

    private void populateTextViews(final View view, final Bundle mappings) {
        int resourceId;
        for (String key : mappings.keySet()) {
            resourceId = Integer.parseInt(key);
            setText(view, resourceId, mappings.getString(key));
        }
    }

    private void populateImageViews(final View view, final Bundle mappings) {
        Object drawable;
        ImageView imageView;
        int resourceId;

        for (String key : mappings.keySet()) {
            drawable = mappings.get(key);
            if (drawable == null) {
                continue;
            }

            resourceId = Integer.parseInt(key);
            imageView = (ImageView) view.findViewById(resourceId);

            if (drawable instanceof String) {
                populateImageViewFromString(imageView, String.valueOf(drawable));
            } else if (drawable instanceof Integer) {
                imageView.setImageResource((Integer) drawable);
            }
        }
    }

    private void populateImageViewFromString(final ImageView imageView, final String filename) {
        final String path = mContext.getExternalFilesDir(null) + "/" + filename + ".png";
        final File image = new File(path);
        if (image.exists()) {
            imageView.setImageURI(Uri.fromFile(image));
        } else {
            imageView.setImageResource(R.drawable.ic_launcher);
        }
    }
}
