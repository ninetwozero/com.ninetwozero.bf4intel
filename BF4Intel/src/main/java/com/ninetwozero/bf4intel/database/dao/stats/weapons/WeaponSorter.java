package com.ninetwozero.bf4intel.database.dao.stats.weapons;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;

public class WeaponSorter extends AbstractSorter<WeaponStatistics> {

    @Override
    public WeaponStatistics sort(SortMode mode) {
        return null;
    }

    @Override
    protected WeaponStatistics sortByProgress() {
        return null;
    }

    @Override
    protected WeaponStatistics sortByCategory() {
        return null;
    }
}
