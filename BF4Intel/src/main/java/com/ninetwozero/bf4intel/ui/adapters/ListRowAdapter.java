package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseIntelAdapter;
import com.ninetwozero.bf4intel.datatypes.ListRow;
import com.ninetwozero.bf4intel.datatypes.ListRowType;

import java.io.File;
import java.util.List;

import static com.ninetwozero.bf4intel.datatypes.ListRowType.HEADING;
import static com.ninetwozero.bf4intel.datatypes.ListRowType.SIDE_HEADING;
import static com.ninetwozero.bf4intel.datatypes.ListRowType.SIDE_REGULAR;
import static com.ninetwozero.bf4intel.datatypes.ListRowType.SIDE_REGULAR_CHILD;

public class ListRowAdapter extends BaseIntelAdapter<ListRow> {
    public ListRowAdapter(final Context context, final List<ListRow> items) {
        super(context, items);
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
        final ListRow item = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(item.getLayout(), viewGroup, false);
        }
        return populateViewFromItem(view, item);
    }

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
        final String path = context.getExternalFilesDir(null) + "/" + filename + ".png";
        final File image = new File(path);
        if (image.exists()) {
            imageView.setImageURI(Uri.fromFile(image));
        } else {
            imageView.setImageResource(R.drawable.ic_launcher);
        }
    }
}
