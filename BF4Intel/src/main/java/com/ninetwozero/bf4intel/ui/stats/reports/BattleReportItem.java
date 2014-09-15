package com.ninetwozero.bf4intel.ui.stats.reports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.stats.reports.GameReport;
import com.ninetwozero.bf4intel.json.stats.reports.MatchResult;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.levels.LevelImageMap;
import com.ninetwozero.bf4intel.resources.maps.levels.LevelStringMap;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListAdapter;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;
import com.squareup.picasso.Picasso;

public class BattleReportItem implements BaseListItem {

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
        return SimpleListAdapter.RowType.ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View view) {
        BattleReportHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_battlereport, null);
            holder = getBattleReportHolder(view);

            view.setTag(holder);
        } else {
            holder = (BattleReportHolder) view.getTag();
        }

        if(holder.reportMapImage != null) {
            Picasso.with(context).load(LevelImageMap.get(report.getMapName())).into(holder.reportMapImage);
        }

        holder.mapName.setText(LevelStringMap.get(report.getMapName()));
        holder.gameMode.setText(GameModeStringMap.get(report.getGameMode()));
        holder.serverName.setText(report.getServerName());

        holder.roundTime.setText(DateTimeUtils.toLiteral(report.getDuration()));
        holder.date.setText(DateTimeUtils.toRelative(report.getCreatedAt()));
        holder.userMatchResult.setText(matchResult(report.findMatchResultFor(soldierId)));
        holder.userMatchResult.setTextColor(matchResultColour(report.findMatchResultFor(soldierId)));
        return view;
    }

    private BattleReportHolder getBattleReportHolder(View view) {
        BattleReportHolder holder = new BattleReportHolder();
        holder.reportMapImage = (ImageView) view.findViewById(R.id.battlereport_map_image);
        holder.mapName = (TextView) view.findViewById(R.id.map_name);
        holder.gameMode = (TextView) view.findViewById(R.id.game_mode);
        holder.serverName = (TextView) view.findViewById(R.id.server_name);
        holder.roundTime = (TextView) view.findViewById(R.id.round_time);
        holder.date = (TextView) view.findViewById(R.id.date);
        holder.userMatchResult = (TextView) view.findViewById(R.id.user_match_result);
        return holder;
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

    private static class BattleReportHolder {

        public ImageView reportMapImage;
        public TextView mapName;
        public TextView gameMode;
        public TextView serverName;
        public TextView roundTime;
        public TextView date;
        public TextView userMatchResult;
    }
}
