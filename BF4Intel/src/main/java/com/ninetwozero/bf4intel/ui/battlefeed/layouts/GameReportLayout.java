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
import com.ninetwozero.bf4intel.resources.maps.levels.LevelStringMap;
import com.ninetwozero.bf4intel.resources.maps.PersonalHighlightStringMap;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

public class GameReportLayout extends BaseLayoutPopulator implements EventLayout<GameReportEvent> {
    private final int[] teamWraps = new int[]{R.id.wrap_team1, R.id.wrap_team2};
    private int colorBlack;
    private int colorGreen;
    private int colorRed;

    @Override
    public void populateView(final Context context, final View view, final GameReportEvent event) {
        final Resources resources = view.getResources();
        colorBlack = resources.getColor(R.color.black);
        colorGreen = resources.getColor(R.color.green);
        colorRed = resources.getColor(R.color.red);

        setText(view, R.id.server_name, event.getServerName());
        setText(view, R.id.map_name, LevelStringMap.get(event.getMap()));
        setText(view, R.id.game_mode, GameModeStringMap.get(event.getGameMode()));

        String[] key = (String[]) event.getTeams().keySet().toArray();
        setupTeam(key[0], event, view, R.id.team1, R.id.score1, R.id.progress1);
        setupTeam(key[1], event, view, R.id.team2, R.id.score2, R.id.progress2);

        final TextView titleTextView = (TextView) view.findViewById(R.id.highlight_title);
        final TextView valueTextView = (TextView) view.findViewById(R.id.highlight_value);
        final boolean hasPersonalHighlight = event.getPersonalHighlight() != null;
        if (hasPersonalHighlight) {
            titleTextView.setText(PersonalHighlightStringMap.get(event.getPersonalHighlight().getType()));
            valueTextView.setText(NumberFormatter.format(event.getPersonalHighlight().getScore()));
        }
        titleTextView.setVisibility(hasPersonalHighlight? View.VISIBLE : View.GONE);
        valueTextView.setVisibility(hasPersonalHighlight? View.VISIBLE : View.GONE);

        setText(view, R.id.player_ranking, "#" + event.getPlayerRanking());
        setText(view, R.id.player_spm, NumberFormatter.format(event.getSpm()));
        setText(view, R.id.player_score, NumberFormatter.format(event.getScore()));
        setText(view, R.id.player_kills, NumberFormatter.format(event.getKillCount()));
        setText(view, R.id.player_kdr, String.valueOf(event.getKdRatio()));
        setText(view, R.id.player_skill, String.valueOf(event.getSkill()));
    }

    private void setupTeam(String key, GameReportEvent event, View view, int teamId, int scoreId, int progressId) {
        final Team team = event.getTeams().get(key);
        final int statusColor = team.isWinner() ? colorGreen : colorRed;
        final int index = Integer.parseInt(key) - 1;

        final View teamWrapView = view.findViewById(teamWraps[index]);
        final TextView teamName = (TextView) teamWrapView.findViewById(teamId);
        final TextView teamScore = (TextView) teamWrapView.findViewById(scoreId);
        final ProgressBar progressBar = (ProgressBar) teamWrapView.findViewById(progressId);

        teamName.setText(team.getName());
        teamScore.setText(NumberFormatter.format(team.getFinalscore()));
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
}
