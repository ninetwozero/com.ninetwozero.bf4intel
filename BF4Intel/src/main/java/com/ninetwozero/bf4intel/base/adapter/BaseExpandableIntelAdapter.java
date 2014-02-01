package com.ninetwozero.bf4intel.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public abstract class BaseExpandableIntelAdapter<T> extends BaseExpandableListAdapter {
    protected static final float OPACITY_NORMAL = 1.0f;
    protected static final float OPACITY_FADED = 0.5f;

    protected final Context context;
    protected final LayoutInflater layoutInflater;

    public BaseExpandableIntelAdapter(final Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public long getChildId(final int groupPosition, final int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(final int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(final int groupPosition, final int childPosition) {
        return true;
    }

    @Override
    public abstract View getGroupView(
        final int position,
        final boolean isExpanded,
        View convertView,
        final ViewGroup viewGroup
    );

    @Override
    public abstract View getChildView(
        final int group,
        final int child,
        final boolean isLastChild,
        View convertView,
        final ViewGroup viewGroup
    );

    public void setText(final View view, final int resourceId, final int stringResource) {
        ((TextView) view.findViewById(resourceId)).setText(stringResource);
    }

    public void setText(final View view, final int resourceId, final String string) {
        ((TextView) view.findViewById(resourceId)).setText(string);
    }

    public void setImage(final View view, final int resourceId, final int imageResource) {
        ((ImageView) view.findViewById(resourceId)).setImageResource(imageResource);
    }

    public void setVisibility(final View view, final int resourceId, final int value) {
        view.findViewById(resourceId).setVisibility(value);
    }
    public void loadImage(final View view, final int resourceId, final String url) {
        Picasso.with(context).load(url).into((ImageView) view.findViewById(resourceId));
    }

    public void setProgress(final View view, final int resourceId, final int current, final int max) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setMax(max);
        progressBar.setProgress(current);
    }

    public abstract int getChildrenCount(final int groupPosition);
    public abstract int getGroupCount();
}
