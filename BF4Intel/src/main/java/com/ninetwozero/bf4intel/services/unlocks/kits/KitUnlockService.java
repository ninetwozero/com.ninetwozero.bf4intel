package com.ninetwozero.bf4intel.services.unlocks.kits;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.kits.KitUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.kits.KitUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlocks;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class KitUnlockService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildKitUnlocksURL(
                    soldier.getString(Keys.Soldier.ID),
                    soldier.getString(Keys.Soldier.NAME),
                    soldier.getInt(Keys.Soldier.PLATFORM)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final KitUnlocks unlocks = fromJson(json, KitUnlocks.class);
                    boolean success = true;

                    final KitUnlockDAO kitUnlockDAO = new KitUnlockDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        KitUnlockMapSorter.sort(unlocks.getUnlockMap())
                    );

                    if (!kitUnlockDAO.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new KitUnlocksRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
