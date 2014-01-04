package com.ninetwozero.bf4intel.ui.stats.reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.battlereports.SummaryBattleReport;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

import java.util.List;

public class BattleReportAdapter extends BaseAdapter {
    private Context context;
    final LayoutInflater layoutInflater;
    private List<SummaryBattleReport> items;

    private String personaId;

    public BattleReportAdapter(final Context context, final String personaId) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);

        this.personaId = personaId;
    }

    @Override
    public SummaryBattleReport getItem(final int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return Long.parseLong(getItem(position).getId());
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {
        final SummaryBattleReport report = getItem(position);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_battlereport, parent, false);
        }

        ((TextView) view.findViewById(R.id.map_name)).setText(LevelStringMap.get(report.getMap()));
        ((TextView) view.findViewById(R.id.game_mode)).setText(GameModeStringMap.get(report.getGameMode()));
        ((TextView) view.findViewById(R.id.server_name)).setText(report.getServerName());

        ((TextView) view.findViewById(R.id.round_time)).setText(DateTimeUtils.toLiteral(report.getDuration()));
        ((TextView) view.findViewById(R.id.date)).setText(DateTimeUtils.toRelative(context, report.getDate()));
        return view;
    }

    public void setItems(final List<SummaryBattleReport> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
