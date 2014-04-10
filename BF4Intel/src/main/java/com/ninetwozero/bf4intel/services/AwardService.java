package com.ninetwozero.bf4intel.services;

import com.ninetwozero.bf4intel.dao.awards.AwardSorter;
import com.ninetwozero.bf4intel.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.events.awards.AwardsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.resources.Keys;

import java.net.URL;

public class AwardService extends BaseDaoService<AwardsDAO, AwardsRefreshedEvent> {
    @Override
    protected AwardsRefreshedEvent getEventToBroadcast(boolean result) {
        return new AwardsRefreshedEvent(result);
    }

    @Override
    protected AwardsDAO parseJsonIntoDao(String json) {
        final Awards awards = fromJson(json, Awards.class);
        return new AwardsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            AwardSorter.sort(awards)
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildAwardsURL(
            soldier.getString(Keys.Soldier.ID),
            soldier.getInt(Keys.Soldier.PLATFORM)
        );
    }
}
