package com.ninetwozero.bf4intel.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponUnlockMapSorter {
    public static SortedWeaponUnlocks sort(Map<String, List<WeaponUnlockContainer>> unlockMap) {
        final Map<String, List<WeaponUnlockContainer>> map = new HashMap<String, List<WeaponUnlockContainer>>();
        for (String key : unlockMap.keySet()) {
            final List<WeaponUnlockContainer> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return new SortedWeaponUnlocks(map);
    }
}
