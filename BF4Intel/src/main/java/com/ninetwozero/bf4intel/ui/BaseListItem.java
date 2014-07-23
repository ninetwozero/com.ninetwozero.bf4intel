package com.ninetwozero.bf4intel.ui;

import android.view.LayoutInflater;
import android.view.View;

public interface BaseListItem {
    int getViewType();
    View getView(LayoutInflater inflater, View convertView);
}
