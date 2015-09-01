package com.ninetwozero.bf4intel.services.unlocks.kits;

import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.kits.KitUnlockMapSorter;
import com.ninetwozero.bf4intel.events.unlocks.kits.KitUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.network.Bf4IntelService;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.KitUnlocks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;
import com.ninetwozero.bf4intel.ui.unlocks.UnlockTabFragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KitUnlockService extends BaseDaoService<KitUnlockDAO, KitUnlocksRefreshedEvent> {
    private static final Set<String> SKIP_LIST = new HashSet<String>() {
        {
            add("1BFBE29D-CA2D-42B8-88DD-8E5C481E40FC"); // AK5C
            add("D9899D8B-2D67-4A13-975E-F0D5E8E1525D"); // RFB
            add("9892E6C9-6317-4558-953D-94BA2DCD355C"); // QBS-09
        }
    };

    @Override
    protected KitUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new KitUnlocksRefreshedEvent(result);
    }

    @Override
    protected KitUnlockDAO parseJsonIntoDao(String json) {
        final KitUnlocks unlocks = fromJson(json, KitUnlocks.class);
        final Map<String, List<KitItemUnlockContainer>> deDuplicatedUnlocks = removeDuplicates(unlocks.getUnlockMap());
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(UnlockTabFragment.KEY_SORT_MODE, 0));
        return new KitUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            new KitUnlockMapSorter(deDuplicatedUnlocks).sort(sortMode)
        );
    }

    private Map<String, List<KitItemUnlockContainer>> removeDuplicates(Map<String, List<KitItemUnlockContainer>> unlocks) {
        Map<String, List<KitItemUnlockContainer>> unlockMap = new HashMap<String, List<KitItemUnlockContainer>>();
        for (String key : unlocks.keySet()) {
            unlockMap.put(key, getUniqueList(unlocks.get(key)));
        }
        return unlockMap;
    }

    @Override
    protected URL getUrlForService() {
        return Bf4IntelService.buildKitUnlocksURL(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM)
        );
    }

    private static List<KitItemUnlockContainer> getUniqueList(final List<KitItemUnlockContainer> containers) {
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
