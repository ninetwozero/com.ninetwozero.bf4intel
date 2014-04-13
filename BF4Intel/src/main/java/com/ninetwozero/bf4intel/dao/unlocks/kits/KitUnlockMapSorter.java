package com.ninetwozero.bf4intel.dao.unlocks.kits;

import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KitUnlockMapSorter {
    public static SortedKitUnlocks sort(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        List<KitItemUnlockContainer> list = new ArrayList<KitItemUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        Collections.sort(list);
        return new SortedKitUnlocks(list);
    }
}
