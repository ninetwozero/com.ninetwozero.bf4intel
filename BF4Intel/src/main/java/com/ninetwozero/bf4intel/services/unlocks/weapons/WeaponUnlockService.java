package com.ninetwozero.bf4intel.services.unlocks.weapons;

import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.dao.unlocks.weapons.WeaponUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.weapons.WeaponUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;

import java.net.URL;

public class WeaponUnlockService extends BaseDaoService<WeaponUnlockDAO, WeaponUnlocksRefreshedEvent> {
    @Override
    protected WeaponUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new WeaponUnlocksRefreshedEvent(result);
    }

    @Override
    protected WeaponUnlockDAO parseJsonIntoDao(String json) {
        final WeaponUnlocks unlocks = fromJson(json, WeaponUnlocks.class);
        return new WeaponUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            WeaponUnlockMapSorter.sort(unlocks.getUnlockMap())
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