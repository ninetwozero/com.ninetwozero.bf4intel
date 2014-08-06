package com.ninetwozero.bf4intel.database.dao.unlocks;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractUnlockSorter<T, D extends Comparable> extends AbstractSorter<T>{
    private final Map<String, List<D>> unlockMap;

    public AbstractUnlockSorter(final Map<String, List<D>> unlockMap) {
        this.unlockMap = unlockMap;
    }

    @Override
    protected T sortByProgress() {
        List<D> list = new ArrayList<D>();
        for (String key : unlockMap.keySet()) {
            final List<D> items = unlockMap.get(key);
            addCategoryToItems(items, key);
            list.addAll(items);
        }
        Collections.sort(list);
        return createNewObject(list);
    }

    @Override
    protected T sortByCategory() {
        List<D> list = new ArrayList<D>();
        for (String key : getCategoryOrder()) {
            if (unlockMap.containsKey(key)) {
                final List<D> items = unlockMap.get(key);
                addCategoryToItems(items, key);
                list.addAll(items);
            }
        }
        return createNewObject(list);
    }

    protected abstract void addCategoryToItems(final List<D> items, final String category);
    protected abstract T createNewObject(final List<D> list);
    protected abstract String[] getCategoryOrder();
}
