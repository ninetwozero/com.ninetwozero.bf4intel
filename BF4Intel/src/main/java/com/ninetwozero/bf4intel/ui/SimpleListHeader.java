package com.ninetwozero.bf4intel.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;

public class SimpleListHeader implements BaseListItem {

    private final int headerResource;
    private final int layoutFile;

    public SimpleListHeader(int headerResource) {
        this.headerResource = headerResource;
        this.layoutFile = R.layout.list_header;
    }

    public SimpleListHeader(int headerResource, int layoutFile) {
        this.headerResource = headerResource;
        this.layoutFile = layoutFile;
    }

    @Override
    public int getViewType() {
        return SimpleListAdapter.RowType.HEADER.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        if (view == null) {
            view = inflater.inflate(layoutFile, null);
        }

        ((TextView) view.findViewById(R.id.list_header)).setText(headerResource);
        return view;
    }
}
