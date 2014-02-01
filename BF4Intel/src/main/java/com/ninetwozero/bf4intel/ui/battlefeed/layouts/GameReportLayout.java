package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameReportEvent;
import com.ninetwozero.bf4intel.json.battlereports.Team;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;
import com.ninetwozero.bf4intel.resources.maps.PersonalHighlightStringMap;

public class GameReportLayout extends BaseLayoutPopulator implements EventLayout<GameReportEvent> {
    @Override
    public void populateView(final Context context, final View view, final GameReportEvent event) {
        final Resources resources = view.getResources();
        final int colorBlack = resources.getColor(R.color.black);
        final int colorGreen = resources.getColor(R.color.green);
        final int colorRed = resources.getColor(R.color.red);
        final int[] teamWraps = new int[]{R.id.wrap_team1, R.id.wrap_team2};

        setText(view, R.id.server_name, event.getServerName());
        setText(view, R.id.map_name, LevelStringMap.get(event.getMap()));
        setText(view, R.id.game_mode, GameModeStringMap.get(event.getGameMode()));

        for (String key : event.getTeams().keySet()) {
            final Team team = event.getTeams().get(key);
            final int statusColor = team.isWinner() ? colorGreen : colorRed;
            final int index = Integer.parseInt(key) - 1;

            final View teamWrapView = view.findViewById(teamWraps[index]);
            final TextView teamName = (TextView) teamWrapView.findViewById(R.id.team);
            final TextView teamScore = (TextView) teamWrapView.findViewById(R.id.score);
            final ProgressBar progressBar = (ProgressBar) teamWrapView.findViewById(R.id.progress);

            teamName.setText(team.getName());
            teamScore.setText(String.format("%,d", team.getFinalscore()));
            progressBar.setMax(team.getStartingScore());
            progressBar.setProgress(team.getFinalscore());

            if (team.getId() == event.getPlayerTeamId()) {
                teamName.setTextColor(statusColor);
                teamName.setTypeface(null, Typeface.BOLD);
                teamScore.setTextColor(statusColor);
                teamScore.setTypeface(null, Typeface.BOLD);
            } else {
                teamName.setTextColor(colorBlack);
                teamName.setTypeface(null, Typeface.NORMAL);
                teamScore.setTextColor(colorBlack);
                teamScore.setTypeface(null, Typeface.NORMAL);
            }
        }

        final TextView titleTextView = (TextView) view.findViewById(R.id.highlight_title);
        final TextView valueTextView = (TextView) view.findViewById(R.id.highlight_value);
        final boolean hasPersonalHighlight = event.getPersonalHighlight() != null;
        if (hasPersonalHighlight) {
            titleTextView.setText(PersonalHighlightStringMap.get(event.getPersonalHighlight().getType()));
            valueTextView.setText(String.format("%,d", event.getPersonalHighlight().getScore()));
        }
        titleTextView.setVisibility(hasPersonalHighlight? View.VISIBLE : View.GONE);
        valueTextView.setVisibility(hasPersonalHighlight? View.VISIBLE : View.GONE);

        setText(view, R.id.player_ranking, "#" + event.getPlayerRanking());
        setText(view, R.id.player_spm, String.format("%,d", event.getSpm()));
        setText(view, R.id.player_score, String.format("%,d", event.getScore()));
        setText(view, R.id.player_kills, String.format("%,d", event.getKillCount()));
        setText(view, R.id.player_kdr, String.valueOf(event.getKdRatio()));
        setText(view, R.id.player_skill, String.valueOf(event.getSkill()));
    }
}
