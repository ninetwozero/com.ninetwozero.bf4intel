package com.ninetwozero.bf4intel.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public abstract class BaseIntelAdapter<T> extends BaseAdapter {
    protected final Context context;
    protected final LayoutInflater layoutInflater;
    protected List<T> itemsList;

    public BaseIntelAdapter(final Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public BaseIntelAdapter(final Context context, final List<T> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Deprecated
    public BaseIntelAdapter(List<T> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemsList == null ? 0 : itemsList.size();
    }

    @Override
    public T getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public abstract long getItemId(int position);

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public void setText(final View view, final int resourceId, final int stringResource) {
        ((TextView) view.findViewById(resourceId)).setText(stringResource);
    }

    public void setText(final View view, final int resourceId, final String string) {
        ((TextView) view.findViewById(resourceId)).setText(string);
    }

    public void setImage(final View view, final int resourceId, final int imageResource) {
        ((ImageView) view.findViewById(resourceId)).setImageResource(imageResource);
    }

    public void loadImage(final View view, final int resourceId, final String url) {
        Picasso.with(context).load(url).into((ImageView) view.findViewById(resourceId));
    }

    public void setProgress(final View view, final int resourceId, final int current, final int max) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setProgress(current);
        progressBar.setMax(max);
    }

    public void setVisibilty(final View view, final int resourceId, final int state) {
        view.findViewById(resourceId).setVisibility(state);
    }

    public void setItems(final List<T> items) {
        this.itemsList = items;
        notifyDataSetChanged();
    }

    public void appendItems(final List<T> items) {
        if (this.itemsList == null) {
            this.itemsList = items;
        } else {
            this.itemsList.addAll(items);
        }
        notifyDataSetChanged();
    }
}
