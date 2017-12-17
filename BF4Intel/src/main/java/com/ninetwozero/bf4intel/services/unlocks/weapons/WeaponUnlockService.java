package com.ninetwozero.bf4intel.services.unlocks.weapons;

import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.weapons.WeaponUnlockSorter;
import com.ninetwozero.bf4intel.events.unlocks.weapons.WeaponUnlocksRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.WeaponUnlocks;
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

public class WeaponUnlockService extends BaseDaoService<WeaponUnlockDAO, WeaponUnlocksRefreshedEvent> {
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
            add("0B126F24-23D7-4E64-B88E-CD139A2F4D23"); // rorsch-mk-1 not showed in unlocks
            add("517ECEA8-C418-4D8E-8C56-B6D225512DA5"); //m60 ult
        }
    };

    @Override
    protected WeaponUnlocksRefreshedEvent getEventToBroadcast(boolean result) {
        return new WeaponUnlocksRefreshedEvent(result);
    }

    @Override
    protected WeaponUnlockDAO parseJsonIntoDao(String json) {
        final WeaponUnlocks unlocks = fromJson(json, WeaponUnlocks.class);
        final Map<String, List<WeaponUnlockContainer>> unlockMap = removeInGameUnique(unlocks.getUnlockMap());
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(UnlockTabFragment.KEY_SORT_MODE, 0));
        return new WeaponUnlockDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            new WeaponUnlockSorter(unlockMap).sort(sortMode)
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildWeaponUnlocksURL(
            soldier.getString(Keys.Soldier.ID),
            soldier.getInt(Keys.Soldier.PLATFORM)
        );
    }


    private Map<String, List<WeaponUnlockContainer>> removeInGameUnique(Map<String, List<WeaponUnlockContainer>> unlocks) {
        Map<String, List<WeaponUnlockContainer>> unlockMap = new HashMap<String, List<WeaponUnlockContainer>>();
        for (String key : unlocks.keySet()) {
            unlockMap.put(key, getUniqueList(unlocks.get(key)));
        }
        return unlockMap;
    }

    private List<WeaponUnlockContainer> getUniqueList(final List<WeaponUnlockContainer> containers) {
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