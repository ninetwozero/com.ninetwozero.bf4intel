package com.ninetwozero.bf4intel.model.stats.details;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListHeader;
import com.ninetwozero.bf4intel.ui.stats.details.DetailedStatsListItem;
import com.ninetwozero.bf4intel.utils.NumberFormatter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StatsDetailsGrouped {

    private static final String EMPTY_VALUE = "-";
    private static final String PERCENT_SIGN = "%";
    private static final String METERS = "m";
    private List<BaseListItem> details = new ArrayList<BaseListItem>();

    public StatsDetailsGrouped(StatsDetails.GeneralStats details) {
        buildDetailsList(details);
    }

    public List<BaseListItem> getDetails() {
        return details;
    }

    private void buildDetailsList(StatsDetails.GeneralStats details) {
        this.details.add(new SimpleListHeader(R.string.multiplayer_score));
        this.details.add(generateGroupForMultiplayerScores(details));
        this.details.add(new SimpleListHeader(R.string.general_score));
        this.details.add(generateGroupForScores(details));
        this.details.add(new SimpleListHeader(R.string.game_modes));
        this.details.add(generateGroupForGameModes(details));
        this.details.add(new SimpleListHeader(R.string.team_score));
        this.details.add(generateGroupForTeamScores(details));
        this.details.add(new SimpleListHeader(R.string.extra_score));
        this.details.add(generateGroupForExtras(details));
        this.details.add(new SimpleListHeader(R.string.game_mode_extra));
        this.details.add(generateGroupForGameModeExtras(details));
    }

    private DetailStatsGroup generateGroupForMultiplayerScores(final StatsDetails.GeneralStats details) {
        final List<BaseListItem> items = new ArrayList<BaseListItem>();
        items.add(new DetailedStatsListItem(R.string.assault_score, stringValueOf(details.getAssaultScore())));
        items.add(new DetailedStatsListItem(R.string.engineer_score, stringValueOf(details.getEngineerScore())));
        items.add(new DetailedStatsListItem(R.string.support_score, stringValueOf(details.getSupportScore())));
        items.add(new DetailedStatsListItem(R.string.recon_score, stringValueOf(details.getReconScore())));
        items.add(new DetailedStatsListItem(R.string.commander_score, stringValueOf(details.getCommanderScore())));
        items.add(new DetailedStatsListItem(R.string.squad_score, stringValueOf(details.getSquadScore())));
        items.add(new DetailedStatsListItem(R.string.vehicle_score, stringValueOf(details.getVehicleScore())));
        items.add(new DetailedStatsListItem(R.string.award_score, stringValueOf(details.getAwardScore())));
        items.add(new DetailedStatsListItem(R.string.unlock_score, stringValueOf(details.getUnlockScore())));
        items.add(new DetailedStatsListItem(R.string.total_score, stringValueOf(details.getTotalScore())));
        return new DetailStatsGroup(items);
    }

    private DetailStatsGroup generateGroupForScores(final StatsDetails.GeneralStats details) {
        final List<BaseListItem> items = new ArrayList<BaseListItem>();
        items.add(new DetailedStatsListItem(R.string.kills,  stringValueOf(details.getKills())));
        items.add(new DetailedStatsListItem(R.string.deaths,  stringValueOf(details.getDeaths())));
        items.add(new DetailedStatsListItem(R.string.kill_assists,  stringValueOf(details.getKillAssits())));
        items.add(new DetailedStatsListItem(R.string.kd_ratio,  stringValueOf(details.getKdRatio())));
        items.add(new DetailedStatsListItem(R.string.wins,  stringValueOf(details.getWins())));
        items.add(new DetailedStatsListItem(R.string.losses,  stringValueOf(details.getLosses())));
        items.add(new DetailedStatsListItem(R.string.shots_fired,  stringValueOf(details.getShotsFired())));
        items.add(new DetailedStatsListItem(R.string.shots_hits,  stringValueOf(details.getShotsHit())));
        items.add(new DetailedStatsListItem(R.string.accuracy,  stringValueOf(details.getAccuracy(), PERCENT_SIGN)));
        return new DetailStatsGroup(items);
    }
    private DetailStatsGroup generateGroupForGameModes(StatsDetails.GeneralStats details) {
        final List<BaseListItem> items = new ArrayList<BaseListItem>();
        items.add(new DetailedStatsListItem(R.string.conquest,  stringValueOf(details.getConquest())));
        items.add(new DetailedStatsListItem(R.string.rush,  stringValueOf(details.getRush())));
        items.add(new DetailedStatsListItem(R.string.death_match,  stringValueOf(details.getDeathMatch())));
        items.add(new DetailedStatsListItem(R.string.domination,  stringValueOf(details.getDomination())));
        items.add(new DetailedStatsListItem(R.string.capture_the_flag,  stringValueOf(details.getCaptureTheFlag())));
        items.add(new DetailedStatsListItem(R.string.obliteration,  stringValueOf(details.getObliteration())));
        items.add(new DetailedStatsListItem(R.string.air_superiority, stringValueOf(0 /*details.getAirSuperiority()*/)));
        items.add(new DetailedStatsListItem(R.string.defuse, stringValueOf(0 /*details.getDefuse()*/)));
        return new DetailStatsGroup(items);
    }

    private DetailStatsGroup generateGroupForTeamScores(StatsDetails.GeneralStats details) {
        final List<BaseListItem> items = new ArrayList<BaseListItem>();
        items.add(new DetailedStatsListItem(R.string.repairs,  stringValueOf(details.getRepairs())));
        items.add(new DetailedStatsListItem(R.string.revives,  stringValueOf(details.getRevives())));
        items.add(new DetailedStatsListItem(R.string.heals,  stringValueOf(details.getHeals())));
        items.add(new DetailedStatsListItem(R.string.resupplies,  stringValueOf(details.getResupplies())));
        items.add(new DetailedStatsListItem(R.string.avenger_kills,  stringValueOf(details.getAvengerKills())));
        items.add(new DetailedStatsListItem(R.string.savior_kills,  stringValueOf(details.getSaviorKills())));
        items.add(new DetailedStatsListItem(R.string.suppression_assists,  stringValueOf(details.getSuppresionAssists())));
        items.add(new DetailedStatsListItem(R.string.quits,  stringValueOf(details.getQuits(), PERCENT_SIGN)));
        return new DetailStatsGroup(items);
    }

    private DetailStatsGroup generateGroupForExtras(StatsDetails.GeneralStats details) {
        final List<BaseListItem> items = new ArrayList<BaseListItem>();
        items.add(new DetailedStatsListItem(R.string.dogtag_taken,  stringValueOf(details.getDogtagTaken())));
        items.add(new DetailedStatsListItem(R.string.vehicles_destroyed,  stringValueOf(details.getVehiclesDestroyed())));
        items.add(new DetailedStatsListItem(R.string.vehicle_damage,  stringValueOf(details.getVehicleDamage())));
        items.add(new DetailedStatsListItem(R.string.headshots,  stringValueOf(details.getHeadshots())));
        items.add(new DetailedStatsListItem(R.string.longest_headshot,  stringValueOf(details.getLongestHeadshot(), METERS)));
        items.add(new DetailedStatsListItem(R.string.highest_kill_streak,  stringValueOf(details.getHighestKillStreak())));
        items.add(new DetailedStatsListItem(R.string.nemesis_kills,  stringValueOf(details.getNemesisKills())));
        items.add(new DetailedStatsListItem(R.string.highest_nemesis_streak,  stringValueOf(details.getHighestNemesisStreak())));
        return new DetailStatsGroup(items);
    }

    private DetailStatsGroup generateGroupForGameModeExtras(StatsDetails.GeneralStats details) {
        final List<BaseListItem> items = new ArrayList<BaseListItem>();
        items.add(new DetailedStatsListItem(R.string.flags_captured,  stringValueOf(details.getFlagsCaptured())));
        items.add(new DetailedStatsListItem(R.string.flags_defended,  stringValueOf(details.getFlagsDefended())));
        return new DetailStatsGroup(items);
    }

    private String stringValueOf(int value) {
        return value != 0 ? String.valueOf(NumberFormat.getInstance(Locale.getDefault()).format(value)) : EMPTY_VALUE;
    }

    private String stringValueOf(double value) {
        return value != 0.0 ? NumberFormatter.format(value) : EMPTY_VALUE;
    }

    private String stringValueOf(double value, String postfix) {
        if (value == 0.0) {
            return EMPTY_VALUE;
        }
        return String.format(
            Locale.getDefault(),
            "%s%s",
            NumberFormatter.format(value),
            postfix
        );
    }
}
