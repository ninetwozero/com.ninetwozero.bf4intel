package com.ninetwozero.bf4intel.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WeaponUnlockSorter {
    private static final String[] CATEGORY_ORDER = new String[] {
        "wA", "wC", "waS", "wL", "waPDW", "wD", "wSR", "wH", "wG", "wSPk", "wX",
    };

    public static SortedWeaponUnlocks sort(final Map<String, List<WeaponUnlockContainer>> unlockMap, final SortMode mode) {
        if (mode == SortMode.PROGRESS) {
            return sortItemsByProgress(unlockMap);
        } else {
            return sortItemsByCategory(unlockMap);
        }
    }

    private static SortedWeaponUnlocks sortItemsByProgress(Map<String, List<WeaponUnlockContainer>> unlockMap) {
        List<WeaponUnlockContainer> list = new ArrayList<WeaponUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        Collections.sort(list);
        return new SortedWeaponUnlocks(list);
    }

    private static SortedWeaponUnlocks sortItemsByCategory(Map<String, List<WeaponUnlockContainer>> unlockMap) {
        List<WeaponUnlockContainer> list = new ArrayList<WeaponUnlockContainer>();
        for (String key : CATEGORY_ORDER) {
            if (unlockMap.containsKey(key)) {
                list.addAll(unlockMap.get(key));
            }
        }
        return new SortedWeaponUnlocks(list);
    }
}
