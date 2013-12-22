package com.ninetwozero.bf4intel.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public abstract class BaseIntelAdapter<T> extends BaseAdapter {

    protected final List<T> itemsList;
    protected final Context context;

    public BaseIntelAdapter(List<T> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public T getItem(int position){
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

    public void setProgress(final View view, final int resourceId, final int current, final int max) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(resourceId);
        progressBar.setProgress(current);
        progressBar.setMax(max);
    }
}
