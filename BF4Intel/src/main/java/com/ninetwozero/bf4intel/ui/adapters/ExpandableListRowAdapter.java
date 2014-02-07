package com.ninetwozero.bf4intel.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.ListRow;
import com.ninetwozero.bf4intel.datatypes.ListRowType;

import java.io.File;
import java.util.List;

import static com.ninetwozero.bf4intel.datatypes.ListRowType.HEADING;
import static com.ninetwozero.bf4intel.datatypes.ListRowType.SIDE_HEADING;
import static com.ninetwozero.bf4intel.datatypes.ListRowType.SIDE_REGULAR;
import static com.ninetwozero.bf4intel.datatypes.ListRowType.SIDE_REGULAR_CHILD;

public class ExpandableListRowAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ListRow> items;

    public ExpandableListRowAdapter(final Context context, final List<ListRow> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getGroupTypeCount() {
        return ListRowType.SIZE;
    }

    @Override
    public int getGroupType(final int position) {
        return getGroup(position).getType().ordinal();
    }

    @Override
    public int getGroupCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getChildrenCount(final int position) {
        return getGroup(position).getChildCount();
    }

    @Override
    public ListRow getGroup(final int position) {
        return items.get(position);
    }

    @Override
    public ListRow getChild(final int position, final int childPosition) {
        return getGroup(position).getChild(childPosition);
    }

    @Override
    public long getGroupId(final int position) {
        return position;
    }

    @Override
    public long getChildId(final int position, final int childPosition) {
        return (position * 100) + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int position, int childPosition) {
        return getGroup(position).getChild(childPosition).getType().isEnabled();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return getGroupCount() == 0;
    }

    @Override
    public long getCombinedChildId(final long position, final long childPosition) {
        return position * 1000 + childPosition * 100;
    }

    @Override
    public long getCombinedGroupId(final long position) {
        return position * 1000;
    }

    @Override
    public View getGroupView(final int position, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final ListRow item = getGroup(position);
        final ListRowType type = item.getType();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(item.getLayout(), viewGroup, false);
        }

        convertView.setEnabled(type != ListRowType.SIDE_HEADING);

        return populateViewFromItem(convertView, item);
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup viewGroup) {
        final ListRow item = getChild(group, child);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(item.getLayout(), viewGroup, false);
        }

        return populateViewFromItem(convertView, item);
    }

    private void setText(final View parent, final int resourceId, final String text) {
        ((TextView) parent.findViewById(resourceId)).setText(text);
    }

    private void setText(final View parent, final int resourceId, final int textResourceId) {
        ((TextView) parent.findViewById(resourceId)).setText(textResourceId);
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

    public void setItems(final List<ListRow> itemsForMenu) {
        this.items = itemsForMenu;
        notifyDataSetChanged();
    }
}
