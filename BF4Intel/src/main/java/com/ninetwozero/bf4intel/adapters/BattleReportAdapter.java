package com.ninetwozero.bf4intel.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.jsonmodels.battlereports.SummaryBattleReport;
import com.ninetwozero.bf4intel.jsonmodels.battlereports.Team;
import com.ninetwozero.bf4intel.resourcemaps.GameModeStringMap;
import com.ninetwozero.bf4intel.resourcemaps.LevelStringMap;
import com.ninetwozero.bf4intel.utils.DateTimeUtils;

import java.util.List;

public class BattleReportAdapter extends BaseAdapter {
    private Context context;
    final LayoutInflater layoutInflater;
    private List<SummaryBattleReport> items;

    private String personaId;
    private int[] teamWraps = new int[] {R.id.wrap_team1, R.id.wrap_team2};

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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final SummaryBattleReport report = getItem(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_battlereport, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.map_name)).setText(LevelStringMap.get(report.getMap()));
        ((TextView) convertView.findViewById(R.id.game_mode)).setText(GameModeStringMap.get(report.getGameMode()));
        ((TextView) convertView.findViewById(R.id.server_name)).setText(report.getServerName());

        for (int i = 0; i < teamWraps.length; i++) {
            final int adjustedIndex = i+1;
            final View wrap = convertView.findViewById(teamWraps[i]);
            final Team team = report.getTeams().get(String.valueOf(adjustedIndex));
            final TextView teamNameView = (TextView) wrap.findViewById(R.id.team);
            final TextView teamScoreView = (TextView) wrap.findViewById(R.id.score);
            final ProgressBar progressBar = (ProgressBar) wrap.findViewById(R.id.progress);

            final boolean playerIsInTeam = report.isPlayerInTeam(personaId, team.getId());
            final int statusColorId = team.isWinner()? R.color.green : R.color.red;
            final int colorId = playerIsInTeam? statusColorId : R.color.black;
            final int colorCode = context.getResources().getColor(colorId);
            final int typeFaceId = playerIsInTeam? Typeface.BOLD : Typeface.NORMAL;

            teamNameView.setText(team.getName());
            teamNameView.setTextColor(colorCode);
            teamNameView.setTypeface(null, typeFaceId);
            teamScoreView.setText(String.valueOf(team.getFinalscore()));
            teamScoreView.setTextColor(colorCode);
            teamScoreView.setTypeface(null, typeFaceId);

            progressBar.setMax(team.getStartingScore());
            progressBar.setProgress(team.getFinalscore());
        }
        ((TextView) convertView.findViewById(R.id.round_time)).setText(DateTimeUtils.toLiteral(report.getDuration()));
        ((TextView) convertView.findViewById(R.id.date)).setText(DateTimeUtils.toRelative(context, report.getDate()));
        return convertView;
    }

    public void setItems(final List<SummaryBattleReport> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
