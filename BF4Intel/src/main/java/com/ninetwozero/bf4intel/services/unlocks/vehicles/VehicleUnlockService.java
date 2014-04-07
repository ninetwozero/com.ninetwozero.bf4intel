package com.ninetwozero.bf4intel.services.unlocks.vehicles;

import android.app.Service;
import android.content.Intent;

import com.ninetwozero.bf4intel.Bf4Intel;
import com.ninetwozero.bf4intel.dao.unlocks.vehicles.VehicleUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.vehicles.VehicleUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.vehicles.VehicleUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlocks;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseApiService;
import com.ninetwozero.bf4intel.utils.BusProvider;

import se.emilsjolander.sprinkles.Transaction;

public class VehicleUnlockService extends BaseApiService {

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        super.onStartCommand(intent, flags, startId);
        Bf4Intel.getRequestQueue().add(
            new SimpleGetRequest<Boolean>(
                UrlFactory.buildVehicleUnlocksURL(
                    soldier.getString(Keys.Soldier.ID),
                    soldier.getInt(Keys.Soldier.PLATFORM)
                ),
                this
            ) {
                @Override
                protected Boolean doParse(String json) {
                    final Transaction transaction = new Transaction();
                    final VehicleUnlocks unlocks = fromJson(json, VehicleUnlocks.class);
                    boolean success = true;

                    final VehicleUnlockDAO vehicleUnlockDAO = new VehicleUnlockDAO(
                        soldier.getString(Keys.Soldier.ID),
                        soldier.getString(Keys.Soldier.NAME),
                        soldier.getInt(Keys.Soldier.PLATFORM),
                        VehicleUnlockMapSorter.sort(unlocks.getUnlockMap())
                    );

                    if (!vehicleUnlockDAO.save(transaction)) {
                        success = false;
                    }

                    transaction.setSuccessful(success);
                    transaction.finish();
                    return success;
                }

                @Override
                protected void deliverResponse(Boolean result) {
                    BusProvider.getInstance().post(new VehicleUnlocksRefreshedEvent(result));
                    stopSelf(startId);
                }
            }
        );
        return Service.START_NOT_STICKY;
    }
}
