package com.ninetwozero.bf4intel.services.stats.details;

import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsDAO;
import com.ninetwozero.bf4intel.dao.stats.details.DetailedStatsGrouper;
import com.ninetwozero.bf4intel.events.stats.details.DetailedStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.details.StatsDetails;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;

import java.net.URL;

public class DetailedStatsService extends BaseDaoService<DetailedStatsDAO, DetailedStatsRefreshedEvent> {
    @Override
    protected DetailedStatsRefreshedEvent getEventToBroadcast(boolean result) {
        return new DetailedStatsRefreshedEvent(result);
    }

    @Override
    protected DetailedStatsDAO parseJsonIntoDao(String json) {
        final StatsDetails.GeneralStats details = fromJson(json, StatsDetails.class).getGeneralStats();
        return new DetailedStatsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            DetailedStatsGrouper.group(details)
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildDetailsURL(
            soldier.getString(Keys.Soldier.ID, ""),
            soldier.getInt(Keys.Soldier.PLATFORM, 0)
        );
    }
}
