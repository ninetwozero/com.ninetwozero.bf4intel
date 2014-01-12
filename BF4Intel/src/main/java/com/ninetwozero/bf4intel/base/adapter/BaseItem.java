package com.ninetwozero.bf4intel.base.adapter;

import android.view.LayoutInflater;
import android.view.View;

public interface BaseItem {

        public int getViewType();
        public View getView(LayoutInflater inflater, View convertView);
}
