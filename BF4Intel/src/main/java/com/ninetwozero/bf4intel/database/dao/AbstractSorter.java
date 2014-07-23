package com.ninetwozero.bf4intel.database.dao;

import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;

public abstract class AbstractSorter<T> {

    public T sort(final SortMode mode){
        if (mode == SortMode.PROGRESS) {
            return sortByProgress();
        } else {
            return sortByCategory();
        }
    }

    protected abstract T sortByProgress();

    protected abstract T sortByCategory();

}
