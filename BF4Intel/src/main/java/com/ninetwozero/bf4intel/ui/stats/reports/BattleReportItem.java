package com.ninetwozero.bf4intel.ui.stats.reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseItem;
import com.ninetwozero.bf4intel.json.stats.reports.GameReport;
import com.ninetwozero.bf4intel.json.stats.reports.MatchResult;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

public class BattleReportItem implements BaseItem {

    private final GameReport report;
    private final int soldierId;
    private final Context context;

    public BattleReportItem(GameReport report, int soldierId, Context context) {
        this.report = report;
        this.soldierId = soldierId;
        this.context = context;
    }

    @Override
    public int getViewType() {
        return BattleReportAdapter.BattleReportType.ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_battlereport, null);
        }

        ((TextView) view.findViewById(R.id.map_name)).setText(LevelStringMap.get(report.getMapName()));
        ((TextView) view.findViewById(R.id.game_mode)).setText(GameModeStringMap.get(report.getGameMode()));
        ((TextView) view.findViewById(R.id.server_name)).setText(report.getServerName());

        ((TextView) view.findViewById(R.id.round_time)).setText(DateTimeUtils.toLiteral(report.getDuration()));
        ((TextView) view.findViewById(R.id.date)).setText(DateTimeUtils.toRelative(report.getCreatedAt()));
        TextView matchResult = (TextView) view.findViewById(R.id.user_match_result);
        matchResult.setText(matchResult(report.findMatchResultFor(soldierId)));
        matchResult.setTextColor(matchResultColour(report.findMatchResultFor(soldierId)));
        return view;
    }

    private int matchResult(MatchResult result) {
        switch (result) {
            case WON:
                return R.string.match_won;
            case LOST:
                return R.string.match_lost;
            case DRAW:
                return R.string.match_draw;
            default:
                return R.string.unknown;
        }
    }

    private int matchResultColour(MatchResult result) {
        int colour;
        switch (result) {
            case WON:
                colour = R.color.battlereport_won;
                break;
            case LOST:
                colour = R.color.battlereport_lost;
                break;
            case DRAW:
                colour = R.color.battlereport_draw;
                break;
            default:
                colour = R.color.black;
                break;
        }
        return context.getResources().getColor(colour);
    }
}
