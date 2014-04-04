package com.ninetwozero.bf4intel.services.stats.details;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsDAO;
import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsGrouper;
import com.ninetwozero.bf4intel.events.stats.details.DetailedStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class DetailedStatsService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildDetailsURL(
                    soldier.getString(Keys.Soldier.ID, ""),
                    soldier.getInt(Keys.Soldier.PLATFORM, 0)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final StatsDetails.GeneralStats details = fromJson(json, StatsDetails.class).getGeneralStats();
                    boolean success = true;

                    final DetailedStatsDAO detailedStatsDAO = new DetailedStatsDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        DetailedStatsGrouper.group(details)
                    );

                    if (!detailedStatsDAO.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new DetailedStatsRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
