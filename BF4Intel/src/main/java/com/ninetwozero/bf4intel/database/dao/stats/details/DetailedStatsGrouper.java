package com.ninetwozero.bf4intel.database.dao.stats.details;

import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsContainer;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsGroup;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsItem;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailedStatsGrouper {
    private static final String EMPTY_VALUE = "-";
    private static final String PERCENT_SIGN = "%";
    private static final String METERS = "m";

    public static DetailedStatsContainer group(StatsDetails.GeneralStats details) {
        final List<DetailedStatsGroup> items = new ArrayList<DetailedStatsGroup>();
        items.add(generateGroupForMultiplayerScores(details));
        items.add(generateGroupForScores(details));
        items.add(generateGroupForGameModes(details));
        items.add(generateGroupForTeamScores(details));
        items.add(generateGroupForExtras(details));
        items.add(generateGroupForGameModeExtras(details));
        return new DetailedStatsContainer(items);
    }

    private static DetailedStatsGroup generateGroupForMultiplayerScores(final StatsDetails.GeneralStats details) {
        final List<DetailedStatsItem> items = new ArrayList<DetailedStatsItem>();
        items.add(new DetailedStatsItem("sc_assault", stringValueOf(details.getAssaultScore())));
        items.add(new DetailedStatsItem("sc_engineer", stringValueOf(details.getEngineerScore())));
        items.add(new DetailedStatsItem("sc_support", stringValueOf(details.getSupportScore())));
        items.add(new DetailedStatsItem("sc_recon", stringValueOf(details.getReconScore())));
        items.add(new DetailedStatsItem("sc_commander", stringValueOf(details.getCommanderScore())));
        items.add(new DetailedStatsItem("sc_squad", stringValueOf(details.getSquadScore())));
        items.add(new DetailedStatsItem("sc_vehicle", stringValueOf(details.getVehicleScore())));
        items.add(new DetailedStatsItem("sc_award", stringValueOf(details.getAwardScore())));
        items.add(new DetailedStatsItem("sc_unlock", stringValueOf(details.getUnlockScore())));
        items.add(new DetailedStatsItem("sc_total", stringValueOf(details.getTotalScore())));
        items.add(new DetailedStatsItem("sc_per_minute", stringValueOf(details.getScorePerMinute())));
        return new DetailedStatsGroup(DetailedStatsGroup.MULTIPLAYER_SCORES, items);
    }

    private static DetailedStatsGroup generateGroupForScores(final StatsDetails.GeneralStats details) {
        final List<DetailedStatsItem> items = new ArrayList<DetailedStatsItem>();
        items.add(new DetailedStatsItem("kills", stringValueOf(details.getKills())));
        items.add(new DetailedStatsItem("deaths", stringValueOf(details.getDeaths())));
        items.add(new DetailedStatsItem("kill_assists", stringValueOf(details.getKillAssists())));
        items.add(new DetailedStatsItem("kd_ratio", stringValueOf(details.getKdRatio())));
        items.add(new DetailedStatsItem("kills_per_minute", stringValueOf(details.getKillsPerMinute())));
        items.add(new DetailedStatsItem("wins", stringValueOf(details.getWins())));
        items.add(new DetailedStatsItem("losses", stringValueOf(details.getLosses())));
        items.add(new DetailedStatsItem("shots_fired", stringValueOf(details.getShotsFired())));
        items.add(new DetailedStatsItem("shots_hits", stringValueOf(details.getShotsHit())));
        items.add(new DetailedStatsItem("accuracy", stringValueOf(details.getAccuracy(), PERCENT_SIGN)));
        return new DetailedStatsGroup(DetailedStatsGroup.GENERAL_SCORES, items);
    }

    private static DetailedStatsGroup generateGroupForGameModes(StatsDetails.GeneralStats details) {
        final List<DetailedStatsItem> items = new ArrayList<DetailedStatsItem>();
        items.add(new DetailedStatsItem("conquest", stringValueOf(details.getConquest())));
        items.add(new DetailedStatsItem("rush", stringValueOf(details.getRush())));
        items.add(new DetailedStatsItem("death_match", stringValueOf(details.getDeathMatch())));
        items.add(new DetailedStatsItem("domination", stringValueOf(details.getDomination())));
        items.add(new DetailedStatsItem("obliteration", stringValueOf(details.getObliteration())));
        items.add(new DetailedStatsItem("defuse", stringValueOf(details.getDefuse())));
        items.add(new DetailedStatsItem("capture_the_flag", stringValueOf(details.getCaptureTheFlag())));
        items.add(new DetailedStatsItem("air_superiority", stringValueOf(details.getAirSuperiority())));
        items.add(new DetailedStatsItem("carrier_assault", stringValueOf(details.getCarrieAssault())));
        items.add(new DetailedStatsItem("chain_link", stringValueOf(details.getChainLink())));
        return new DetailedStatsGroup(DetailedStatsGroup.GAME_MODE_SCORES, items);
    }

    private static DetailedStatsGroup generateGroupForTeamScores(StatsDetails.GeneralStats details) {
        final List<DetailedStatsItem> items = new ArrayList<DetailedStatsItem>();
        items.add(new DetailedStatsItem("repairs", stringValueOf(details.getRepairs())));
        items.add(new DetailedStatsItem("revives", stringValueOf(details.getRevives())));
        items.add(new DetailedStatsItem("heals", stringValueOf(details.getHeals())));
        items.add(new DetailedStatsItem("resupplies", stringValueOf(details.getResupplies())));
        items.add(new DetailedStatsItem("avenger_kills", stringValueOf(details.getAvengerKills())));
        items.add(new DetailedStatsItem("savior_kills", stringValueOf(details.getSaviorKills())));
        items.add(new DetailedStatsItem("suppression_assists", stringValueOf(details.getSuppresionAssists())));
        items.add(new DetailedStatsItem("quits", stringValueOf(details.getQuits(), PERCENT_SIGN)));
        return new DetailedStatsGroup(DetailedStatsGroup.TEAM_SCORES, items);
    }

    private static DetailedStatsGroup generateGroupForExtras(StatsDetails.GeneralStats details) {
        final double scorePerShot = ((double) details.getTotalScore()) / details.getShotsFired();

        final List<DetailedStatsItem> items = new ArrayList<DetailedStatsItem>();
        items.add(new DetailedStatsItem("dogtag_taken", stringValueOf(details.getDogtagTaken())));
        items.add(new DetailedStatsItem("vehicles_destroyed", stringValueOf(details.getVehiclesDestroyed())));
        items.add(new DetailedStatsItem("vehicle_damage", stringValueOf(details.getVehicleDamage())));
        items.add(new DetailedStatsItem("headshots", stringValueOf(details.getHeadshots())));
        items.add(new DetailedStatsItem("longest_headshot", stringValueOf(details.getLongestHeadshot(), METERS)));
        items.add(new DetailedStatsItem("highest_kill_streak", stringValueOf(details.getHighestKillStreak())));
        items.add(new DetailedStatsItem("nemesis_kills", stringValueOf(details.getNemesisKills())));
        items.add(new DetailedStatsItem("highest_nemesis_streak", stringValueOf(details.getHighestNemesisStreak())));
        items.add(new DetailedStatsItem("score_per_shot", stringValueOf(scorePerShot)));
        return new DetailedStatsGroup(DetailedStatsGroup.EXTRA_SCORES, items);
    }

    private static DetailedStatsGroup generateGroupForGameModeExtras(StatsDetails.GeneralStats details) {
        final List<DetailedStatsItem> items = new ArrayList<DetailedStatsItem>();
        items.add(new DetailedStatsItem("flags_captured", stringValueOf(details.getFlagsCaptured())));
        items.add(new DetailedStatsItem("flags_defended", stringValueOf(details.getFlagsDefended())));
        return new DetailedStatsGroup(DetailedStatsGroup.GAME_MODE_EXTRA_SCORES, items);
    }

    private static String stringValueOf(int value) {
        return String.valueOf(NumberFormat.getInstance(Locale.getDefault()).format(value));
    }

    private static String stringValueOf(double value) {
        return NumberFormatter.format(value);
    }

    private static String stringValueOf(double value, String postfix) {
        return String.format(
            Locale.getDefault(),
            "%s%s",
            NumberFormatter.format(value),
            postfix
        );
    }
}
