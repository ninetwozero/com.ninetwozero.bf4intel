package com.ninetwozero.bf4intel.dao.unlocks.kits;

import com.ninetwozero.bf4intel.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KitUnlockMapSorter {
    private static final String[] CATEGORY_ORDER = new String[] { "1", "2", "8", "32" };
    private static final Set<String> SKIP_LIST = new HashSet<String>() {
        {
            add("1BFBE29D-CA2D-42B8-88DD-8E5C481E40FC"); // AK5C
            add("93AC8B6A-D3B4-478F-9420-CDBACCE71C1F"); // RFB
            add("9892E6C9-6317-4558-953D-94BA2DCD355C"); // QBS-09
        }
    };

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
            list.addAll(removeDuplicates(unlockMap.get(key)));
        }
        Collections.sort(list);
        return new SortedKitUnlocks(list);
    }

    private static SortedKitUnlocks sortItemsByCategory(Map<String, List<KitItemUnlockContainer>> unlockMap) {
        List<KitItemUnlockContainer> list = new ArrayList<KitItemUnlockContainer>();
        for (String key : CATEGORY_ORDER) {
            if (unlockMap.containsKey(key)) {
                list.addAll(removeDuplicates(unlockMap.get(key)));
            }
        }
        return new SortedKitUnlocks(list);
    }


    private static List<KitItemUnlockContainer> removeDuplicates(final List<KitItemUnlockContainer> containers) {
        Set<String> guids = new HashSet<String>(SKIP_LIST);
        List<KitItemUnlockContainer> uniqueList = new ArrayList<KitItemUnlockContainer>();

        for (KitItemUnlockContainer container : containers) {
            final String guid = container.getUnlock().getGuid();
            if (guids.contains(guid)) {
                continue;
            }
            guids.add(guid);
            uniqueList.add(container);
        }
        return uniqueList;
    }
}