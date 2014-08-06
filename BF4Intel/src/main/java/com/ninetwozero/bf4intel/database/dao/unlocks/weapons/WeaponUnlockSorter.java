package com.ninetwozero.bf4intel.database.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.database.dao.unlocks.AbstractUnlockSorter;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import java.util.List;
import java.util.Map;

public class WeaponUnlockSorter extends AbstractUnlockSorter<SortedWeaponUnlocks, WeaponUnlockContainer> {
    public static final String[] CATEGORY_ORDER = new String[] {
        "wA", "wC", "waS", "wL", "waPDW", "wD", "wSR", "wH", "wG", "wSPk", "wX",
    };

    public WeaponUnlockSorter(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        super(unlockMap);
    }

    @Override
    protected void addCategoryToItems(final List<WeaponUnlockContainer> validItems, final String category) {
        for (WeaponUnlockContainer unlockContainer : validItems) {
            unlockContainer.setCategory(category);
        }
    }

    @Override
    protected SortedWeaponUnlocks createNewObject(final List<WeaponUnlockContainer> list) {
        return new SortedWeaponUnlocks(list);
    }

    @Override
    protected String[] getCategoryOrder() {
        return CATEGORY_ORDER;
    }
}
