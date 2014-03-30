package com.ninetwozero.bf4intel.services;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.dao.soldieroverview.SoldierOverviewDAO;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.SoldierInformationUpdated;
import com.ninetwozero.bf4intel.utils.SoldierOverviewRefreshedEvent;

import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Transaction;

public class SoldierOverviewService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);

        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildSoldierOverviewURL(
                    soldier.getString(Keys.Soldier.ID, ""),
                    soldier.getInt(Keys.Soldier.PLATFORM, 0)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final SoldierOverview soldierOverview = fromJson(json, SoldierOverview.class);
                    boolean success = true;

                    SoldierOverviewDAO overviewDao = new SoldierOverviewDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        soldierOverview
                    );

                    if (!overviewDao.save(transaction)) {
                        success = false;
                    }

                    SummarizedSoldierStatsDAO statsDao = Query.one(
                        SummarizedSoldierStatsDAO.class,
                        "SELECT * " +
                        "FROM " + SummarizedSoldierStatsDAO.TABLE_NAME + " " +
                        "WHERE soldierId = ? AND platform = ?",
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getInt(Keys.Soldier.PLATFORM)
                    ).get();

                    statsDao.setRank(soldierOverview.getCurrentRank().getLevel());
                    if (soldierOverview.getPersonaInfo() != null) {
                        statsDao.setPicture(soldierOverview.getPersonaInfo().getPicture());
                    }

                    if (!statsDao.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new SoldierInformationUpdated(soldier));
                    BusProvider.getInstance().post(new SoldierOverviewRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
