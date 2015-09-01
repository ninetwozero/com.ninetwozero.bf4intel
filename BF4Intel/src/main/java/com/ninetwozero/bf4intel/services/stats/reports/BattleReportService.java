package com.ninetwozero.bf4intel.services.stats.reports;

import android.app.Service;
import android.content.Intent;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.events.stats.reports.BattleReportsRefreshedEvent;
import com.ninetwozero.bf4intel.network.Bf4IntelService;
import com.ninetwozero.bf4intel.json.stats.reports.BattleReportStatistics;
import com.ninetwozero.bf4intel.json.stats.reports.GameReport;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListHeader;
import com.ninetwozero.bf4intel.ui.stats.reports.BattleReportItem;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.util.ArrayList;
import java.util.List;

public class BattleReportService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        if (intentCount == 1) {
            Bf4Intel.getRequestQueue().add(
                new SimpleGetRequest<List<BaseListItem>>(
                    Bf4IntelService.buildBattleReportsURL(
                        soldier.getString(Keys.Soldier.ID, ""),
                        soldier.getInt(Keys.Soldier.PLATFORM, 0)
                    ),
                    this
                ) {
                    @Override
                    protected List<BaseListItem> doParse(String json) {
                        final List<BaseListItem> items = new ArrayList<BaseListItem>();
                        final JsonObject baseObject = extractFromJson(json, false);
                        if ((baseObject.get("statsTemplate").getAsString().equals("common.warsawerror"))) {
                            return null;
                        }

                        final BattleReportStatistics statistics = gson.fromJson(baseObject, BattleReportStatistics.class);
                        if (statistics.getFavoriteReports().size() > 0) {
                            items.add(new SimpleListHeader(R.string.header_favorite_battlereport));
                            items.addAll(buildBaseItemList(new ArrayList<GameReport>(statistics.getFavoriteReports()), statistics.getSoldierId()));
                        }

                        if (statistics.getStatsGameReports().size() > 0) {
                            items.add(new SimpleListHeader(R.string.header_latest_battlereport));
                            items.addAll(buildBaseItemList(new ArrayList<GameReport>(statistics.getStatsGameReports()), statistics.getSoldierId()));
                        }

                        return items;
                    }

                    @Override
                    protected void deliverResponse(List<BaseListItem> items) {
                        BusProvider.getInstance().post(new BattleReportsRefreshedEvent(items));
                        stopSelf();
                    }

                    private List<BaseListItem> buildBaseItemList(final List<GameReport> reports, final int soldierId) {
                        List<BaseListItem> itemsList = new ArrayList<BaseListItem>();
                        for (GameReport report : reports) {
                            itemsList.add(new BattleReportItem(report, soldierId, getApplicationContext()));
                        }
                        return itemsList;
                    }
                }
            );
        }
        return Service.START_NOT_STICKY;
    }
}
