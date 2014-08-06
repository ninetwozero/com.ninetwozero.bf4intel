package com.ninetwozero.bf4intel.database.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WeaponUnlockSorter extends AbstractSorter<SortedWeaponUnlocks>{
    public static final String[] CATEGORY_ORDER = new String[] {
        "wA", "wC", "waS", "wL", "waPDW", "wD", "wSR", "wH", "wG", "wSPk", "wX",
    };
    private final Map<String, List<WeaponUnlockContainer>> unlockMap;

    public WeaponUnlockSorter(final Map<String, List<WeaponUnlockContainer>> unlockMap) {
        this.unlockMap = unlockMap;
    }

    @Override
    protected SortedWeaponUnlocks sortByProgress() {
        List<WeaponUnlockContainer> list = new ArrayList<WeaponUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            final List<WeaponUnlockContainer> validItems = unlockMap.get(key);
            addCategoryToItems(validItems, key);
            list.addAll(validItems);
        }
        Collections.sort(list);
        return new SortedWeaponUnlocks(list);
    }

    @Override
    protected SortedWeaponUnlocks sortByCategory() {
        List<WeaponUnlockContainer> list = new ArrayList<WeaponUnlockContainer>();
        for (String key : CATEGORY_ORDER) {
            if (unlockMap.containsKey(key)) {
                final List<WeaponUnlockContainer> validItems = unlockMap.get(key);
                addCategoryToItems(validItems, key);
                list.addAll(validItems);
            }
        }
        return new SortedWeaponUnlocks(list);
    }

    private void addCategoryToItems(final List<WeaponUnlockContainer> validItems, final String category) {
        for (WeaponUnlockContainer unlockContainer : validItems) {
            unlockContainer.setCategory(category);
        }
    }
}
