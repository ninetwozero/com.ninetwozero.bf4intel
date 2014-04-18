package com.ninetwozero.bf4intel.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import java.util.*;

public class WeaponUnlockSorter {
    private static final String[] CATEGORY_ORDER = new String[] {
        "wA", "wC", "waS", "wL", "waPDW", "wD", "wSR", "wH", "wG", "wSPk", "wX",
    };
    private static final Set<String> SKIP_LIST = new HashSet<String>() {
        {
            add("6F741867-AE83-CC7D-BFB1-035452D7A5B4"); // usas-12
            add("14E1C33F-D07F-4F67-8513-F0B03B935BA3"); // usas-12-flir
            add("C366F0B4-7CF5-4897-AB3F-EEF5384AEBCD"); // amr-2
            add("C42E9537-08B5-45BE-8FF2-769D0EE1685D"); // m82a3-mid
            add("FBF5309F-FE06-4FCD-9C93-FDBFD0F08380"); // amr-2-mid
            add("2D5D7462-4FF6-4255-B230-81A7233EDD2C"); // amr-2-cqb
            add("75BEDC56-9A79-4013-81EA-1430C97CDDF8"); // m82a3-cqb
            add("4B28E2E7-4F5D-455E-AC2E-C597763F5139"); // m82a3
            add("B21346FC-365F-4BE9-8FE2-A9588A78A4CD"); // m32-mgl
        }
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
                list.addAll(removeInGameUnique(unlockMap.get(key)));
            }
        }
        return new SortedWeaponUnlocks(list);
    }

    private static List<WeaponUnlockContainer> removeInGameUnique(final List<WeaponUnlockContainer> containers) {
        Set<String> guids = new HashSet<String>(SKIP_LIST);
        List<WeaponUnlockContainer> uniqueList = new ArrayList<WeaponUnlockContainer>();

        for (WeaponUnlockContainer container : containers) {
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
