package com.ninetwozero.bf4intel.services.stats.vehicles;

import com.ninetwozero.bf4intel.database.dao.stats.vehicles.VehicleStatsDAO;
import com.ninetwozero.bf4intel.events.stats.vehicles.VehicleStatsRefreshedEvent;
import com.ninetwozero.bf4intel.network.Bf4IntelService;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStatsContainer;
import com.ninetwozero.bf4intel.json.stats.vehicles.VehicleStatistics;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;

import java.net.URL;

public class VehicleStatsService extends BaseDaoService<VehicleStatsDAO, VehicleStatsRefreshedEvent> {
    @Override
    protected VehicleStatsRefreshedEvent getEventToBroadcast(boolean result) {
        return new VehicleStatsRefreshedEvent(result);
    }

    @Override
    protected VehicleStatsDAO parseJsonIntoDao(String json) {
        final VehicleStatistics vehicleStats = fromJson(json, VehicleStatistics.class);
        return new VehicleStatsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            new GroupedVehicleStatsContainer(vehicleStats.fetchGroupVehicles())
        );
    }

    @Override
    protected URL getUrlForService() {
        return Bf4IntelService.buildVehicleStatsURL(
            soldier.getString(Keys.Soldier.ID, ""),
            soldier.getInt(Keys.Soldier.PLATFORM, 0)
        );
    }
}
