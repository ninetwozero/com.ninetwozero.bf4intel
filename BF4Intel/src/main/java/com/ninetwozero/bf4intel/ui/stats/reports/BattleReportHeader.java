package com.ninetwozero.bf4intel.ui.stats.reports;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseItem;

public class BattleReportHeader implements BaseItem {

    private final int headerResource;

    public BattleReportHeader(int headerResource) {
        this.headerResource = headerResource;
    }

    @Override
    public int getViewType() {
        return BattleReportAdapter.BattleReportType.HEADER.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_header_battlereport, null);
        }

        ((TextView) view.findViewById(R.id.battlereport_header)).setText(headerResource);
        return view;
    }
}
