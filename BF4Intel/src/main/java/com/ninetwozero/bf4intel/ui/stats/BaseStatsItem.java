package com.ninetwozero.bf4intel.ui.stats;

import android.view.LayoutInflater;
import android.view.View;

public interface BaseStatsItem {

        public int getViewType();
        public View getView(LayoutInflater inflater, View convertView);
}
