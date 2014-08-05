package com.ninetwozero.bf4intel.services.unlocks.kits;

import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.kits.KitUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlocks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;
import com.ninetwozero.bf4intel.ui.unlocks.UnlockTabFragment;

import java.net.URL;

public class KitUnlockService extends BaseDaoService<KitUnlockDAO, KitUnlocksRefreshedEvent> {
    @Override
    protected KitUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new KitUnlocksRefreshedEvent(result);
    }

    @Override
    protected KitUnlockDAO parseJsonIntoDao(String json) {
        final KitUnlocks unlocks = fromJson(json, KitUnlocks.class);
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(UnlockTabFragment.KEY_SORT_MODE, 0));
        return new KitUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            new KitUnlockMapSorter(unlocks.getUnlockMap()).sort(sortMode)
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
