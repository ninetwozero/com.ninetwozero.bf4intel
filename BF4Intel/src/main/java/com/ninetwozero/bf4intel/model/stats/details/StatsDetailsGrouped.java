package com.ninetwozero.bf4intel.model.stats.details;

import android.util.Pair;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;

import java.util.ArrayList;
import java.util.List;

public class StatsDetailsGrouped {

    private static final String EMPTY_VALUE = "-";
    private static final String PERCENT_SIGN = "%";
    private List<Pair<Integer, String>> multiplayerScore = new ArrayList<Pair<Integer, String>>();
    private List<Pair<Integer, String>> generalScores = new ArrayList<Pair<Integer, String>>();
    private List<Pair<Integer, String>> gameModes = new ArrayList<Pair<Integer, String>>();
    private List<Pair<Integer, String>> teamScores = new ArrayList<Pair<Integer, String>>();
    private List<Pair<Integer, String>> extraScores = new ArrayList<Pair<Integer, String>>();
    private List<Pair<Integer, String>> gameModeExtra = new ArrayList<Pair<Integer, String>>();

    public StatsDetailsGrouped(StatsDetails.GeneralStats details) {
        buildMultiplayerScore(details);
        buildGeneralScores(details);
        buildGameModes(details);
        buildTeamScores(details);
        buildExtraScores(details);
        buildGameModeExtra(details);
    }

    public List<Pair<Integer, String>> getMultiplayerScore() {
        return multiplayerScore;
    }

    public List<Pair<Integer, String>> getGeneralScores() {
        return generalScores;
    }

    public List<Pair<Integer, String>> getGameModes() {
        return gameModes;
    }

    public List<Pair<Integer, String>> getTeamScores() {
        return teamScores;
    }

    public List<Pair<Integer, String>> getExtraScores() {
        return extraScores;
    }

    public List<Pair<Integer, String>> getGameModeExtra() {
        return gameModeExtra;
    }

    private void buildMultiplayerScore(StatsDetails.GeneralStats details) {
        multiplayerScore.add(pairFrom(R.string.assault_score, details.getAssaultScore()));
        multiplayerScore.add(pairFrom(R.string.engineer_score, details.getEngineerScore()));
        multiplayerScore.add(pairFrom(R.string.support_score, details.getSupportScore()));
        multiplayerScore.add(pairFrom(R.string.recon_score, details.getReconScore()));
        multiplayerScore.add(pairFrom(R.string.commander_score, details.getCommanderScore()));
        multiplayerScore.add(pairFrom(R.string.squad_score, details.getSquadScore()));
        multiplayerScore.add(pairFrom(R.string.vehicle_score, details.getVehicleScore()));
        multiplayerScore.add(pairFrom(R.string.award_score, details.getAwardScore()));
        multiplayerScore.add(pairFrom(R.string.unlock_score, details.getUnlockScore()));
        multiplayerScore.add(pairFrom(R.string.total_score, details.getTotalScore()));
    }

    private void buildGeneralScores(StatsDetails.GeneralStats details) {
        generalScores.add(pairFrom(R.string.kills , details.getKills()));
        generalScores.add(pairFrom(R.string.deaths , details.getDeaths()));
        generalScores.add(pairFrom(R.string.kill_assists , details.getKillAssits()));
        generalScores.add(pairFrom(R.string.kd_ratio , details.getKdRatio()));
        generalScores.add(pairFrom(R.string.wins , details.getWins()));
        generalScores.add(pairFrom(R.string.losses , details.getLosses()));
        generalScores.add(pairFrom(R.string.shots_fired , details.getShotsFired()));
        generalScores.add(pairFrom(R.string.shots_hits , details.getShotsHit()));
        generalScores.add(pairFrom(R.string.accuracy , details.getAccuracy(), PERCENT_SIGN));
    }

    private void buildGameModes(StatsDetails.GeneralStats details) {
        gameModes.add(pairFrom(R.string.conquest , details.getConquest()));
        gameModes.add(pairFrom(R.string.rush , details.getRush()));
        gameModes.add(pairFrom(R.string.death_match , details.getDeathMatch()));
        gameModes.add(pairFrom(R.string.domination , details.getDomination()));
        gameModes.add(pairFrom(R.string.capture_the_flag , details.getCaptureTheFlag()));
        gameModes.add(pairFrom(R.string.obliteration , details.getObliteration()));
        gameModes.add(pairFrom(R.string.air_superiority , 0 /*details.getAirSuperiority()*/));
        gameModes.add(pairFrom(R.string.defuse , 0 /*details.getDefuse()*/));
    }

    private void buildTeamScores(StatsDetails.GeneralStats details) {
        teamScores.add(pairFrom(R.string.repairs, details.getRepairs()));
        teamScores.add(pairFrom(R.string.revives, details.getRevives()));
        teamScores.add(pairFrom(R.string.heals, details.getHeals()));
        teamScores.add(pairFrom(R.string.resupplies, details.getResupplies()));
        teamScores.add(pairFrom(R.string.avenger_kills, details.getAvengerKills()));
        teamScores.add(pairFrom(R.string.savior_kills, details.getSaviorKills()));
        teamScores.add(pairFrom(R.string.suppression_assists, details.getSuppresionAssists()));
        teamScores.add(pairFrom(R.string.quits, details.getQuits(), PERCENT_SIGN));
    }

    private void buildExtraScores(StatsDetails.GeneralStats details) {
        extraScores.add(pairFrom(R.string.dogtag_taken , details.getDogtagTaken()));
        extraScores.add(pairFrom(R.string.vehicles_destroyed , details.getVehiclesDestroyed()));
        extraScores.add(pairFrom(R.string.vehicle_damage , details.getVehicleDamage()));
        extraScores.add(pairFrom(R.string.headshots , details.getHeadshots()));
        extraScores.add(pairFrom(R.string.longest_headshot , details.getLongestHeadshot()));
        extraScores.add(pairFrom(R.string.highest_kill_streak , details.getHighestKillStreak()));
        extraScores.add(pairFrom(R.string.nemesis_kills , details.getNemesisKills()));
        extraScores.add(pairFrom(R.string.highest_nemesis_streak , details.getHighestNemesisStreak()));
    }

    private void buildGameModeExtra(StatsDetails.GeneralStats details) {
        gameModeExtra.add(pairFrom(R.string.flags_captured , details.getFlagsCaptured()));
        gameModeExtra.add(pairFrom(R.string.flags_defended , details.getFlagsDefended()));
    }

    private Pair<Integer, String> pairFrom(int resourceId, int value) {
        return new Pair<Integer, String>(resourceId, value != 0 ? String.valueOf(value) : EMPTY_VALUE);
    }

    private Pair<Integer, String> pairFrom(int resourceId, double value) {
        return new Pair<Integer, String>(resourceId, value != 0.0 ? String.format("%.2f",value) : EMPTY_VALUE);
    }

    private Pair<Integer, String> pairFrom(int resourceId, double value, String postfix) {
        return new Pair<Integer, String>(resourceId, value != 0.0 ? String.format("%.2f%s", value, postfix) : EMPTY_VALUE);
    }
}
