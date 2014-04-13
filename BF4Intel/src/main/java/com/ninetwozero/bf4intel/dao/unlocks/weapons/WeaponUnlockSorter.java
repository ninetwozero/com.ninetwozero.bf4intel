package com.ninetwozero.bf4intel.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WeaponUnlockSorter {
    public static SortedWeaponUnlocks sort(Map<String, List<WeaponUnlockContainer>> unlockMap) {
        List<WeaponUnlockContainer> list = new ArrayList<WeaponUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        Collections.sort(list);
        return new SortedWeaponUnlocks(list);
    }
}
