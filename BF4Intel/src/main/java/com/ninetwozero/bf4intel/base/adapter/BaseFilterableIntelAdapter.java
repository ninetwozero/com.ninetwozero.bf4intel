package com.ninetwozero.bf4intel.base.adapter;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

public abstract class BaseFilterableIntelAdapter<T> extends BaseIntelAdapter<T> implements Filterable {
    private Filter filter;
    protected List<T> listWithAllItems;

    public BaseFilterableIntelAdapter(final Context context) {
        super(context);
    }

    @Override
    public void setItems(final List<T> items) {
        super.setItems(items);
        listWithAllItems = items;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter() {
                protected FilterResults performFiltering(final CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {
                        results.values = itemsList;
                        results.count = itemsList.size();
                    } else {
                        results.values = filterItems(constraint);
                        results.count = ((List<T>) results.values).size();
                    }
                    return results;
                }

                @Override
                protected void publishResults(final CharSequence constraint, final FilterResults results) {
                    itemsList = (List<T>) results.values;
                    if (results.count == 0) {
                        notifyDataSetInvalidated();
                    } else {
                        notifyDataSetChanged();
                    }
                }
            };
        }
        return filter;
    }

    protected abstract List<T> filterItems(final CharSequence constraint);
}