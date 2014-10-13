package com.ninetwozero.bf4intel.database.dao.login;

public class SoldierAccessComparator implements java.util.Comparator<SummarizedSoldierStatsDAO> {
    @Override
    public int compare(final SummarizedSoldierStatsDAO lhs, final SummarizedSoldierStatsDAO rhs) {
        if (lhs.getLastAccess() > rhs.getLastAccess()) {
            return -1;
        } else if (lhs.getLastAccess() < rhs.getLastAccess()) {
            return 1;
        } else {
            return 0;
        }
    }
}
