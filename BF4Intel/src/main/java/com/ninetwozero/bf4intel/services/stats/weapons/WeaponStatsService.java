package com.ninetwozero.bf4intel.services.stats.weapons;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.events.stats.weapons.WeaponStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.util.Collections;

import se.emilsjolander.sprinkles.Transaction;

public class WeaponStatsService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildWeaponStatsURL(
                    soldier.getString(Keys.Soldier.ID, ""),
                    soldier.getInt(Keys.Soldier.PLATFORM, 0)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final WeaponStatistics weaponStats = fromJson(json, WeaponStatistics.class);
                    boolean success = true;

                    Collections.sort(weaponStats.getWeaponsList());

                    final WeaponStatsDAO weaponStatsDao = new WeaponStatsDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        weaponStats
                    );

                    if (!weaponStatsDao.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new WeaponStatsRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
