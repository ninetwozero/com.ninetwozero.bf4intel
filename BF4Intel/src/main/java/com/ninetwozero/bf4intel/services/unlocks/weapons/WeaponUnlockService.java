package com.ninetwozero.bf4intel.services.unlocks.weapons;

import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockSorter;
import com.ninetwozero.bf4intel.events.unlocks.weapons.WeaponUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;
import com.ninetwozero.bf4intel.ui.unlocks.UnlockTabFragment;

import java.net.URL;

public class WeaponUnlockService extends BaseDaoService<WeaponUnlockDAO, WeaponUnlocksRefreshedEvent> {
    @Override
    protected WeaponUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new WeaponUnlocksRefreshedEvent(result);
    }

    @Override
    protected WeaponUnlockDAO parseJsonIntoDao(String json) {
        final WeaponUnlocks unlocks = fromJson(json, WeaponUnlocks.class);
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(UnlockTabFragment.KEY_SORT_MODE, 0));
        return new WeaponUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            WeaponUnlockSorter.sort(unlocks.getUnlockMap(), sortMode)
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildWeaponUnlocksURL(
            soldier.getString(Keys.Soldier.ID),
            soldier.getInt(Keys.Soldier.PLATFORM)
        );
    }
}