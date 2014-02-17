package com.ninetwozero.bf4intel.model.stats.details;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseItem;
import com.ninetwozero.bf4intel.base.adapter.BaseListHeader;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.ui.stats.details.DetailsListItem;

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
        detailsList.add(new DetailsListItem(R.string.assault_score, stringValueOf(details.getAssaultScore())));
        detailsList.add(new DetailsListItem(R.string.engineer_score, stringValueOf(details.getEngineerScore())));
        detailsList.add(new DetailsListItem(R.string.support_score, stringValueOf(details.getSupportScore())));
        detailsList.add(new DetailsListItem(R.string.recon_score,  stringValueOf(details.getReconScore())));
        detailsList.add(new DetailsListItem(R.string.commander_score,  stringValueOf(details.getCommanderScore())));
        detailsList.add(new DetailsListItem(R.string.squad_score,  stringValueOf(details.getSquadScore())));
        detailsList.add(new DetailsListItem(R.string.vehicle_score,  stringValueOf(details.getVehicleScore())));
        detailsList.add(new DetailsListItem(R.string.award_score,  stringValueOf(details.getAwardScore())));
        detailsList.add(new DetailsListItem(R.string.unlock_score,  stringValueOf(details.getUnlockScore())));
        detailsList.add(new DetailsListItem(R.string.total_score,  stringValueOf(details.getTotalScore())));

        detailsList.add(new BaseListHeader(R.string.general_score));
        detailsList.add(new DetailsListItem(R.string.kills,  stringValueOf(details.getKills())));
        detailsList.add(new DetailsListItem(R.string.deaths,  stringValueOf(details.getDeaths())));
        detailsList.add(new DetailsListItem(R.string.kill_assists,  stringValueOf(details.getKillAssits())));
        detailsList.add(new DetailsListItem(R.string.kd_ratio,  stringValueOf(details.getKdRatio())));
        detailsList.add(new DetailsListItem(R.string.wins,  stringValueOf(details.getWins())));
        detailsList.add(new DetailsListItem(R.string.losses,  stringValueOf(details.getLosses())));
        detailsList.add(new DetailsListItem(R.string.shots_fired,  stringValueOf(details.getShotsFired())));
        detailsList.add(new DetailsListItem(R.string.shots_hits,  stringValueOf(details.getShotsHit())));
        detailsList.add(new DetailsListItem(R.string.accuracy,  stringValueOf(details.getAccuracy(), PERCENT_SIGN)));

        detailsList.add(new BaseListHeader(R.string.game_modes));
        detailsList.add(new DetailsListItem(R.string.conquest,  stringValueOf(details.getConquest())));
        detailsList.add(new DetailsListItem(R.string.rush,  stringValueOf(details.getRush())));
        detailsList.add(new DetailsListItem(R.string.death_match,  stringValueOf(details.getDeathMatch())));
        detailsList.add(new DetailsListItem(R.string.domination,  stringValueOf(details.getDomination())));
        detailsList.add(new DetailsListItem(R.string.capture_the_flag,  stringValueOf(details.getCaptureTheFlag())));
        detailsList.add(new DetailsListItem(R.string.obliteration,  stringValueOf(details.getObliteration())));
        detailsList.add(new DetailsListItem(R.string.air_superiority, stringValueOf(0 /*details.getAirSuperiority()*/)));
        detailsList.add(new DetailsListItem(R.string.defuse, stringValueOf(0 /*details.getDefuse()*/)));

        detailsList.add(new BaseListHeader(R.string.team_score));
        detailsList.add(new DetailsListItem(R.string.repairs,  stringValueOf(details.getRepairs())));
        detailsList.add(new DetailsListItem(R.string.revives,  stringValueOf(details.getRevives())));
        detailsList.add(new DetailsListItem(R.string.heals,  stringValueOf(details.getHeals())));
        detailsList.add(new DetailsListItem(R.string.resupplies,  stringValueOf(details.getResupplies())));
        detailsList.add(new DetailsListItem(R.string.avenger_kills,  stringValueOf(details.getAvengerKills())));
        detailsList.add(new DetailsListItem(R.string.savior_kills,  stringValueOf(details.getSaviorKills())));
        detailsList.add(new DetailsListItem(R.string.suppression_assists,  stringValueOf(details.getSuppresionAssists())));
        detailsList.add(new DetailsListItem(R.string.quits,  stringValueOf(details.getQuits(), PERCENT_SIGN)));

        detailsList.add(new BaseListHeader(R.string.extra_score));
        detailsList.add(new DetailsListItem(R.string.dogtag_taken,  stringValueOf(details.getDogtagTaken())));
        detailsList.add(new DetailsListItem(R.string.vehicles_destroyed,  stringValueOf(details.getVehiclesDestroyed())));
        detailsList.add(new DetailsListItem(R.string.vehicle_damage,  stringValueOf(details.getVehicleDamage())));
        detailsList.add(new DetailsListItem(R.string.headshots,  stringValueOf(details.getHeadshots())));
        detailsList.add(new DetailsListItem(R.string.longest_headshot,  stringValueOf(details.getLongestHeadshot(), METERS)));
        detailsList.add(new DetailsListItem(R.string.highest_kill_streak,  stringValueOf(details.getHighestKillStreak())));
        detailsList.add(new DetailsListItem(R.string.nemesis_kills,  stringValueOf(details.getNemesisKills())));
        detailsList.add(new DetailsListItem(R.string.highest_nemesis_streak,  stringValueOf(details.getHighestNemesisStreak())));

        detailsList.add(new BaseListHeader(R.string.game_mode_extra));
        detailsList.add(new DetailsListItem(R.string.flags_captured,  stringValueOf(details.getFlagsCaptured())));
        detailsList.add(new DetailsListItem(R.string.flags_defended,  stringValueOf(details.getFlagsDefended())));
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
