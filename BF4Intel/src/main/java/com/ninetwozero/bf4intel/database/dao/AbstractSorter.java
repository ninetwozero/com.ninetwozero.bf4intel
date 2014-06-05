package com.ninetwozero.bf4intel.database.dao;


public abstract class AbstractSorter<T> {

    public abstract T sort();

    protected abstract T sortByProgress();

    protected abstract T sortByCategory();

}
