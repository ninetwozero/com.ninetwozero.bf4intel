package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlereports.Team;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BestInCategory;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.PersonalHighlight;
import com.ninetwozero.bf4intel.resources.maps.GameModeStringMap;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;
import com.ninetwozero.bf4intel.resources.maps.PersonalHighlightStringMap;

import java.util.Map;

public class GameReportEvent extends BaseEvent {
    private String serverName;
    private String map;
    private int gameMode;

    private int playerRanking;
    private int killCount;
    private double kdRatio;
    private int skill;
    private int duration;
    private int score;
    private int spm;

    private Map<String, Team> teams;
    private int playerTeamId;
    private int winningTeamId;

    private BestInCategory bestVehicle;
    private BestInCategory bestWeapon;
    private PersonalHighlight personalHighlight;

    public GameReportEvent(
        final EventType eventType, final String serverName, final String map, final int gameMode, final int playerRanking,
        final int killCount, final double kdRatio, final int skill, final int duration, final int score, final int spm,
        final Map<String, Team> teams, final int playerTeamId, final int winningTeamId,
        final BestInCategory bestVehicle, final BestInCategory bestWeapon, final PersonalHighlight personalHighlight
    ) {
        super(eventType);
        this.serverName = serverName;
        this.map = map;
        this.gameMode = gameMode;
        this.playerRanking = playerRanking;
        this.killCount = killCount;
        this.kdRatio = kdRatio;
        this.skill = skill;
        this.duration = duration;
        this.score = score;
        this.spm = spm;
        this.teams = teams;
        this.playerTeamId = playerTeamId;
        this.winningTeamId = winningTeamId;
        this.bestVehicle = bestVehicle;
        this.bestWeapon = bestWeapon;
        this.personalHighlight = personalHighlight;
    }

    public String getServerName() {
        return serverName;
    }

    public String getMap() {
        return map;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getPlayerRanking() {
        return playerRanking;
    }

    public int getKillCount() {
        return killCount;
    }

    public double getKdRatio() {
        return kdRatio;
    }

    public int getSkill() {
        return skill;
    }

    public int getDuration() {
        return duration;
    }

    public int getScore() {
        return score;
    }

    public int getSpm() {
        return spm;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public int getPlayerTeamId() {
        return playerTeamId;
    }

    public int getWinningTeamId() {
        return winningTeamId;
    }

    public BestInCategory getBestVehicle() {
        return bestVehicle;
    }

    public BestInCategory getBestWeapon() {
        return bestWeapon;
    }

    public PersonalHighlight getPersonalHighlight() {
        return personalHighlight;
    }

    @Override
    public void populateEventSpecificData(final View view) {
        final Resources resources = view.getResources();
        final int colorBlack = resources.getColor(R.color.black);
        final int colorGreen = resources.getColor(R.color.green);
        final int colorRed = resources.getColor(R.color.red);
        final int[] teamWraps = new int[]{R.id.wrap_team1, R.id.wrap_team2};

        ((TextView) view.findViewById(R.id.server_name)).setText(serverName);
        ((TextView) view.findViewById(R.id.map_name)).setText(LevelStringMap.get(map));
        ((TextView) view.findViewById(R.id.game_mode)).setText(GameModeStringMap.get(gameMode));

        for (String key : teams.keySet()) {
            final Team team = teams.get(key);
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

            if (team.getId() == playerTeamId) {
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

        ((TextView) view.findViewById(R.id.highlight_title)).setText(PersonalHighlightStringMap.get(personalHighlight.getType()));
        ((TextView) view.findViewById(R.id.highlight_value)).setText(String.format("%,d", personalHighlight.getScore()));
        ((TextView) view.findViewById(R.id.player_ranking)).setText("#" + playerRanking);
        ((TextView) view.findViewById(R.id.player_spm)).setText(String.format("%,d", spm));
        ((TextView) view.findViewById(R.id.player_score)).setText(String.format("%,d", score));
        ((TextView) view.findViewById(R.id.player_kills)).setText(String.format("%,d", killCount));
        ((TextView) view.findViewById(R.id.player_kdr)).setText(String.valueOf(kdRatio));
        ((TextView) view.findViewById(R.id.player_skill)).setText(String.valueOf(skill));
    }
}
