package com.ninetwozero.bf4intel.services.stats.vehicles;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.stats.vehicles.VehicleStatsDAO;
import com.ninetwozero.bf4intel.events.stats.weapons.WeaponStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStatsContainer;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStatistics;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class VehicleStatsService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildVehicleStatsURL(
                    soldier.getString(Keys.Soldier.ID, ""),
                    soldier.getInt(Keys.Soldier.PLATFORM, 0)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final VehicleStatistics vehicleStats = fromJson(json, VehicleStatistics.class);
                    boolean success = true;

                    final VehicleStatsDAO vehicleStatsDAO = new VehicleStatsDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        new GroupedVehicleStatsContainer(vehicleStats.fetchGroupVehicles())
                    );

                    if (!vehicleStatsDAO.save(transaction)) {
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
