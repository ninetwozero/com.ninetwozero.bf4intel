package com.ninetwozero.bf4intel.database.dao;


import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;

public abstract class AbstractSorter<T> {

    public abstract T sort(final SortMode mode);

    protected abstract T sortByProgress();

    protected abstract T sortByCategory();

}
