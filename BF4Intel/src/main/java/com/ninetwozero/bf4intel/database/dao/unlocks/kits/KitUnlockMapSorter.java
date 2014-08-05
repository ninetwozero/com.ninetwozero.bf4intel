package com.ninetwozero.bf4intel.database.dao.unlocks.kits;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KitUnlockMapSorter extends AbstractSorter<SortedKitUnlocks> {
    public static final String[] CATEGORY_ORDER = new String[] { "1", "2","32", "8"};
    private static final Set<String> SKIP_LIST = new HashSet<String>() {
        {
            add("1BFBE29D-CA2D-42B8-88DD-8E5C481E40FC"); // AK5C
            add("D9899D8B-2D67-4A13-975E-F0D5E8E1525D"); // RFB
            add("9892E6C9-6317-4558-953D-94BA2DCD355C"); // QBS-09
        }
    };

    private Map<String, List<KitItemUnlockContainer>> unlockMap;

    public KitUnlockMapSorter(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        this.unlockMap = unlockMap;
    }
    @Override
    protected SortedKitUnlocks sortByProgress() {
        List<KitItemUnlockContainer> list = new ArrayList<KitItemUnlockContainer>();
        for (String key : unlockMap.keySet()) {
            final List<KitItemUnlockContainer> allUnlocks = removeDuplicates(unlockMap.get(key));
            addKitIdToItems(allUnlocks, key);
            list.addAll(allUnlocks);
        }
        Collections.sort(list);
        return new SortedKitUnlocks(list);
    }

    @Override
    protected SortedKitUnlocks sortByCategory() {
        List<KitItemUnlockContainer> list = new ArrayList<KitItemUnlockContainer>();
        for (String key : CATEGORY_ORDER) {
            if (unlockMap.containsKey(key)) {
                final List<KitItemUnlockContainer> allUnlocks = removeDuplicates(unlockMap.get(key));
                addKitIdToItems(allUnlocks, key);
                list.addAll(allUnlocks);
            }
        }
        return new SortedKitUnlocks(list);
    }

    private static void addKitIdToItems(final List<KitItemUnlockContainer> allUnlocks, final String key) {
        for (KitItemUnlockContainer container : allUnlocks) {
            container.setKitId(Integer.parseInt(key));
        }
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