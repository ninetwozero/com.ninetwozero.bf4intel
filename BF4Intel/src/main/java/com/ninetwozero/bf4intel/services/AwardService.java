package com.ninetwozero.bf4intel.services;

import com.ninetwozero.bf4intel.database.dao.awards.AwardSorter;
import com.ninetwozero.bf4intel.database.dao.awards.AwardsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.events.awards.AwardsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.awards.AwardGridFragment;

import java.net.URL;

public class AwardService extends BaseDaoService<AwardsDAO, AwardsRefreshedEvent> {
    @Override
    protected AwardsRefreshedEvent getEventToBroadcast(boolean result) {
        return new AwardsRefreshedEvent(result);
    }

    @Override
    protected AwardsDAO parseJsonIntoDao(String json) {
        final Awards awards = fromJson(json, Awards.class);
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(AwardGridFragment.AWARD_SORT_MODE, 0));
        return new AwardsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            new AwardSorter(awards, sortMode).sort()
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
