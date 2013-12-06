package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameReportEvent;
import com.ninetwozero.bf4intel.json.battlereports.Team;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;
import com.ninetwozero.bf4intel.resources.maps.PersonalHighlightStringMap;

public class GameReportUiBinder implements EventUiBinder<GameReportEvent> {
    @Override
    public void populateView(final Context context, final View view, final GameReportEvent event) {
        final Resources resources = view.getResources();
        final int colorBlack = resources.getColor(R.color.black);
        final int colorGreen = resources.getColor(R.color.green);
        final int colorRed = resources.getColor(R.color.red);
        final int[] teamWraps = new int[]{R.id.wrap_team1, R.id.wrap_team2};

        ((TextView) view.findViewById(R.id.server_name)).setText(event.getServerName());
        ((TextView) view.findViewById(R.id.map_name)).setText(LevelStringMap.get(event.getMap()));
        ((TextView) view.findViewById(R.id.game_mode)).setText(GameModeStringMap.get(event.getGameMode()));

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

        ((TextView) view.findViewById(R.id.highlight_title)).setText(
            PersonalHighlightStringMap.get(event.getPersonalHighlight().getType())
        );
        ((TextView) view.findViewById(R.id.highlight_value)).setText(
            String.format("%,d", event.getPersonalHighlight().getScore())
        );
        ((TextView) view.findViewById(R.id.player_ranking)).setText("#" + event.getPlayerRanking());
        ((TextView) view.findViewById(R.id.player_spm)).setText(String.format("%,d", event.getSpm()));
        ((TextView) view.findViewById(R.id.player_score)).setText(String.format("%,d", event.getScore()));
        ((TextView) view.findViewById(R.id.player_kills)).setText(String.format("%,d", event.getKillCount()));
        ((TextView) view.findViewById(R.id.player_kdr)).setText(String.valueOf(event.getKdRatio()));
        ((TextView) view.findViewById(R.id.player_skill)).setText(String.valueOf(event.getSkill()));
    }
}
