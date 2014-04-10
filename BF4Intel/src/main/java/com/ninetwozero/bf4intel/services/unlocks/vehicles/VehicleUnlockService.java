package com.ninetwozero.bf4intel.services.unlocks.vehicles;

import com.ninetwozero.bf4intel.dao.unlocks.vehicles.VehicleUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.vehicles.VehicleUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.vehicles.VehicleUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.VehicleUnlocks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;

import java.net.URL;

public class VehicleUnlockService extends BaseDaoService<VehicleUnlockDAO, VehicleUnlocksRefreshedEvent> {
    @Override
    protected VehicleUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new VehicleUnlocksRefreshedEvent(result);
    }

    @Override
    protected VehicleUnlockDAO parseJsonIntoDao(String json) {
        final VehicleUnlocks unlocks = fromJson(json, VehicleUnlocks.class);
        return new VehicleUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            VehicleUnlockMapSorter.sort(unlocks.getUnlockMap())
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildVehicleUnlocksURL(
            soldier.getString(Keys.Soldier.ID),
            soldier.getInt(Keys.Soldier.PLATFORM)
        );
    }
}