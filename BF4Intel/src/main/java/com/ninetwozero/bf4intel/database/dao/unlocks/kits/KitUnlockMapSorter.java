package com.ninetwozero.bf4intel.database.dao.unlocks.kits;

import com.ninetwozero.bf4intel.database.dao.unlocks.UnlockSorter;
import com.ninetwozero.bf4intel.json.unlocks.KitItemUnlockContainer;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import java.util.List;
import java.util.Map;

public class KitUnlockMapSorter extends UnlockSorter<SortedKitUnlocks, KitItemUnlockContainer> {
    public static final String[] CATEGORY_ORDER = new String[] { "1", "2","32", "8"};

    public KitUnlockMapSorter(final Map<String, List<KitItemUnlockContainer>> unlockMap) {
        super(unlockMap);
    }

    @Override
    protected void addCategoryToItems(final List<KitItemUnlockContainer> items, final String category) {
        for (KitItemUnlockContainer container : items) {
            container.setKitId(Integer.parseInt(category));
        }
    }

    @Override
    protected SortedKitUnlocks createNewObject(final List<KitItemUnlockContainer> list) {
        return new SortedKitUnlocks(list);
    }

    @Override
    protected String[] getCategoryOrder() {
        return CATEGORY_ORDER;
    }
}