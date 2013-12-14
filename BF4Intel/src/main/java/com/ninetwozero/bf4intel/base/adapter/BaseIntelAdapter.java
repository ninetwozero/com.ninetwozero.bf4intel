package com.ninetwozero.bf4intel.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
}
