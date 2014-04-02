package com.ninetwozero.bf4intel.services;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.awards.AwardSorter;
import com.ninetwozero.bf4intel.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.events.awards.AwardsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class AwardService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildAwardsURL(
                    soldier.getString(Keys.Soldier.ID),
                    soldier.getInt(Keys.Soldier.PLATFORM)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final Awards awards = fromJson(json, Awards.class);
                    boolean success = true;

                    final AwardsDAO awardsDao = new AwardsDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        AwardSorter.sort(awards)
                    );

                    if (!awardsDao.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new AwardsRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
