package com.ninetwozero.bf4intel.model.stats.details;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseItem;
import com.ninetwozero.bf4intel.base.adapter.BaseListHeader;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.ui.stats.details.DetailedStatsListItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StatsDetailsGrouped {

    private static final String EMPTY_VALUE = "-";
    private static final String PERCENT_SIGN = "%";
    private static final String METERS = "m";
    private List<BaseItem> detailsList = new ArrayList<BaseItem>();

    public StatsDetailsGrouped(StatsDetails.GeneralStats details) {
        buildDetailsList(details);
    }

    public List<BaseItem> getDetailsList() {
        return detailsList;
    }

    private void buildDetailsList(StatsDetails.GeneralStats details) {
        detailsList.add(new BaseListHeader(R.string.multiplayer_score));
        detailsList.add(new DetailedStatsListItem(R.string.assault_score, stringValueOf(details.getAssaultScore())));
        detailsList.add(new DetailedStatsListItem(R.string.engineer_score, stringValueOf(details.getEngineerScore())));
        detailsList.add(new DetailedStatsListItem(R.string.support_score, stringValueOf(details.getSupportScore())));
        detailsList.add(new DetailedStatsListItem(R.string.recon_score,  stringValueOf(details.getReconScore())));
        detailsList.add(new DetailedStatsListItem(R.string.commander_score,  stringValueOf(details.getCommanderScore())));
        detailsList.add(new DetailedStatsListItem(R.string.squad_score,  stringValueOf(details.getSquadScore())));
        detailsList.add(new DetailedStatsListItem(R.string.vehicle_score,  stringValueOf(details.getVehicleScore())));
        detailsList.add(new DetailedStatsListItem(R.string.award_score,  stringValueOf(details.getAwardScore())));
        detailsList.add(new DetailedStatsListItem(R.string.unlock_score,  stringValueOf(details.getUnlockScore())));
        detailsList.add(new DetailedStatsListItem(R.string.total_score,  stringValueOf(details.getTotalScore())));

        detailsList.add(new BaseListHeader(R.string.general_score));
        detailsList.add(new DetailedStatsListItem(R.string.kills,  stringValueOf(details.getKills())));
        detailsList.add(new DetailedStatsListItem(R.string.deaths,  stringValueOf(details.getDeaths())));
        detailsList.add(new DetailedStatsListItem(R.string.kill_assists,  stringValueOf(details.getKillAssits())));
        detailsList.add(new DetailedStatsListItem(R.string.kd_ratio,  stringValueOf(details.getKdRatio())));
        detailsList.add(new DetailedStatsListItem(R.string.wins,  stringValueOf(details.getWins())));
        detailsList.add(new DetailedStatsListItem(R.string.losses,  stringValueOf(details.getLosses())));
        detailsList.add(new DetailedStatsListItem(R.string.shots_fired,  stringValueOf(details.getShotsFired())));
        detailsList.add(new DetailedStatsListItem(R.string.shots_hits,  stringValueOf(details.getShotsHit())));
        detailsList.add(new DetailedStatsListItem(R.string.accuracy,  stringValueOf(details.getAccuracy(), PERCENT_SIGN)));

        detailsList.add(new BaseListHeader(R.string.game_modes));
        detailsList.add(new DetailedStatsListItem(R.string.conquest,  stringValueOf(details.getConquest())));
        detailsList.add(new DetailedStatsListItem(R.string.rush,  stringValueOf(details.getRush())));
        detailsList.add(new DetailedStatsListItem(R.string.death_match,  stringValueOf(details.getDeathMatch())));
        detailsList.add(new DetailedStatsListItem(R.string.domination,  stringValueOf(details.getDomination())));
        detailsList.add(new DetailedStatsListItem(R.string.capture_the_flag,  stringValueOf(details.getCaptureTheFlag())));
        detailsList.add(new DetailedStatsListItem(R.string.obliteration,  stringValueOf(details.getObliteration())));
        detailsList.add(new DetailedStatsListItem(R.string.air_superiority, stringValueOf(0 /*details.getAirSuperiority()*/)));
        detailsList.add(new DetailedStatsListItem(R.string.defuse, stringValueOf(0 /*details.getDefuse()*/)));

        detailsList.add(new BaseListHeader(R.string.team_score));
        detailsList.add(new DetailedStatsListItem(R.string.repairs,  stringValueOf(details.getRepairs())));
        detailsList.add(new DetailedStatsListItem(R.string.revives,  stringValueOf(details.getRevives())));
        detailsList.add(new DetailedStatsListItem(R.string.heals,  stringValueOf(details.getHeals())));
        detailsList.add(new DetailedStatsListItem(R.string.resupplies,  stringValueOf(details.getResupplies())));
        detailsList.add(new DetailedStatsListItem(R.string.avenger_kills,  stringValueOf(details.getAvengerKills())));
        detailsList.add(new DetailedStatsListItem(R.string.savior_kills,  stringValueOf(details.getSaviorKills())));
        detailsList.add(new DetailedStatsListItem(R.string.suppression_assists,  stringValueOf(details.getSuppresionAssists())));
        detailsList.add(new DetailedStatsListItem(R.string.quits,  stringValueOf(details.getQuits(), PERCENT_SIGN)));

        detailsList.add(new BaseListHeader(R.string.extra_score));
        detailsList.add(new DetailedStatsListItem(R.string.dogtag_taken,  stringValueOf(details.getDogtagTaken())));
        detailsList.add(new DetailedStatsListItem(R.string.vehicles_destroyed,  stringValueOf(details.getVehiclesDestroyed())));
        detailsList.add(new DetailedStatsListItem(R.string.vehicle_damage,  stringValueOf(details.getVehicleDamage())));
        detailsList.add(new DetailedStatsListItem(R.string.headshots,  stringValueOf(details.getHeadshots())));
        detailsList.add(new DetailedStatsListItem(R.string.longest_headshot,  stringValueOf(details.getLongestHeadshot(), METERS)));
        detailsList.add(new DetailedStatsListItem(R.string.highest_kill_streak,  stringValueOf(details.getHighestKillStreak())));
        detailsList.add(new DetailedStatsListItem(R.string.nemesis_kills,  stringValueOf(details.getNemesisKills())));
        detailsList.add(new DetailedStatsListItem(R.string.highest_nemesis_streak,  stringValueOf(details.getHighestNemesisStreak())));

        detailsList.add(new BaseListHeader(R.string.game_mode_extra));
        detailsList.add(new DetailedStatsListItem(R.string.flags_captured,  stringValueOf(details.getFlagsCaptured())));
        detailsList.add(new DetailedStatsListItem(R.string.flags_defended,  stringValueOf(details.getFlagsDefended())));
    }

    private String stringValueOf(int value) {
        return value != 0 ? String.valueOf(NumberFormat.getInstance(Locale.getDefault()).format(value)) : EMPTY_VALUE;
    }

    private String stringValueOf(double value) {
        return value != 0.0 ? String.format(Locale.getDefault(), "%.2f",value) : EMPTY_VALUE;
    }

    private String stringValueOf(double value, String postfix) {
        return value != 0.0 ? String.format(Locale.getDefault(), "%.2f%s", value, postfix) : EMPTY_VALUE;
    }
}
