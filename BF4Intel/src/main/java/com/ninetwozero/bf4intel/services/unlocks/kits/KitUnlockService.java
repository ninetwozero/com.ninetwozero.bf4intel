package com.ninetwozero.bf4intel.services.unlocks.kits;

import com.ninetwozero.bf4intel.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.kits.KitUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.kits.KitUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlocks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseSingleDaoApiService;

import java.net.URL;

public class KitUnlockService extends BaseSingleDaoApiService<KitUnlockDAO, KitUnlocksRefreshedEvent> {
    @Override
    protected KitUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new KitUnlocksRefreshedEvent(result);
    }

    @Override
    protected KitUnlockDAO parseJsonIntoDao(String json) {
        final KitUnlocks unlocks = fromJson(json, KitUnlocks.class);
        return new KitUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            KitUnlockMapSorter.sort(unlocks.getUnlockMap())
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildKitUnlocksURL(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM)
        );
    }
}
