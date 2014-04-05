package com.ninetwozero.bf4intel.dao.unlocks.kits;

import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitUnlockMapSorter {
    public static SortedKitUnlocks sort(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        final Map<String, List<KitItemUnlockContainer>> map = new HashMap<String, List<KitItemUnlockContainer>>();
        for (String key : unlockMap.keySet()) {
            final List<KitItemUnlockContainer> unlocks = unlockMap.get(key);
            Collections.sort(unlocks);
            map.put(key, unlocks);
        }
        return new SortedKitUnlocks(map);
    }
}
