package com.ninetwozero.bf4intel.services.stats.weapons;

import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponSorter;
import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.events.stats.weapons.WeaponStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.weapons.Weapon;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;
import com.ninetwozero.bf4intel.ui.stats.weapons.WeaponStatsFragment;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class WeaponStatsService extends BaseDaoService<WeaponStatsDAO, WeaponStatsRefreshedEvent> {
    @Override
    protected WeaponStatsRefreshedEvent getEventToBroadcast(boolean result) {
        return new WeaponStatsRefreshedEvent(result);
    }

    @Override
    protected WeaponStatsDAO parseJsonIntoDao(String json) {
        WeaponStatistics weaponStats = fromJson(json, WeaponStatistics.class);
        Collections.sort(weaponStats.getWeaponsList());
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(WeaponStatsFragment.WEAPON_SORT_MODE, 0));
        List<Weapon> weaponList = new WeaponSorter(weaponStats.getWeaponsList()).sort(sortMode);
        weaponStats.setWeaponsList(weaponList);
        return new WeaponStatsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            weaponStats
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildWeaponStatsURL(
            soldier.getString(Keys.Soldier.ID, ""),
            soldier.getInt(Keys.Soldier.PLATFORM, 0)
        );
    }
}
