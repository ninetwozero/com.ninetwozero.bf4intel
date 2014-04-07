package com.ninetwozero.bf4intel.services.unlocks.weapons;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.weapons.WeaponUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class WeaponUnlockService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildWeaponUnlocksURL(
                    soldier.getString(Keys.Soldier.ID),
                    soldier.getInt(Keys.Soldier.PLATFORM)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final WeaponUnlocks unlocks = fromJson(json, WeaponUnlocks.class);
                    boolean success = true;

                    final WeaponUnlockDAO weaponUnlockDAO = new WeaponUnlockDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        WeaponUnlockMapSorter.sort(unlocks.getUnlockMap())
                    );

                    if (!weaponUnlockDAO.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new WeaponUnlocksRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
