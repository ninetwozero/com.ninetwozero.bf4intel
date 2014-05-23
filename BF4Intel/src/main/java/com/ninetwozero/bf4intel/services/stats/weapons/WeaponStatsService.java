package com.ninetwozero.bf4intel.services.stats.weapons;

import com.ninetwozero.bf4intel.database.dao.stats.weapons.WeaponStatsDAO;
import com.ninetwozero.bf4intel.events.stats.weapons.WeaponStatsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;

import java.net.URL;
import java.util.Collections;

public class WeaponStatsService extends BaseDaoService<WeaponStatsDAO, WeaponStatsRefreshedEvent> {
    @Override
    protected WeaponStatsRefreshedEvent getEventToBroadcast(boolean result) {
        return new WeaponStatsRefreshedEvent(result);
    }

    @Override
    protected WeaponStatsDAO parseJsonIntoDao(String json) {
        final WeaponStatistics weaponStats = fromJson(json, WeaponStatistics.class);
        Collections.sort(weaponStats.getWeaponsList());
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
