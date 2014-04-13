package com.ninetwozero.bf4intel.dao.unlocks.kits;

import com.ninetwozero.bf4intel.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KitUnlockMapSorter {
    public static SortedKitUnlocks sort(final Map<String, List<KitItemUnlockContainer>> unlockMap, final SortMode mode) {
        if (mode == SortMode.PROGRESS) {
            return sortItemsByProgress(unlockMap);
        } else {
            return sortItemsByCategory(unlockMap);
        }
    }

    private static SortedKitUnlocks sortItemsByProgress(Map<String, List<KitItemUnlockContainer>> unlockMap) {
        List<KitItemUnlockContainer> list = new ArrayList<KitItemUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        Collections.sort(list);
        return new SortedKitUnlocks(list);
    }

    private static SortedKitUnlocks sortItemsByCategory(Map<String, List<KitItemUnlockContainer>> unlockMap) {
        List<KitItemUnlockContainer> list = new ArrayList<KitItemUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            list.addAll(unlockMap.get(key));
        }
        return new SortedKitUnlocks(list);
    }
}