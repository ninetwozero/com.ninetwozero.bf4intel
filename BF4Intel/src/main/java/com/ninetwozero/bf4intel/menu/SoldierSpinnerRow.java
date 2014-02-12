package com.ninetwozero.bf4intel.menu;

import android.content.Intent;

import com.ninetwozero.bf4intel.interfaces.ListRowElement;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;

import java.util.List;

public class SoldierSpinnerRow implements ListRowElement {
    private List<SummarizedSoldierStats> soldiers;

    public SoldierSpinnerRow(List<SummarizedSoldierStats> soldiers) {
        this.soldiers = soldiers;
    }

    @Override
    public String getTitle() {
        return getClass().getSimpleName();
    }

    @Override
    public ListRowType getType() {
        return ListRowType.SIDE_SOLDIER;
    }

    @Override
    public int getLayout() {
        return ListRowType.getResource(getType());
    }

    @Override
    public Intent getIntent() {
        return null;
    }

    @Override
    public boolean hasIntent() {
        return false;
    }

    @Override
    public boolean hasFragmentType() {
        return false;
    }

    public List<SummarizedSoldierStats> getSoldierStats() {
        return soldiers;
    }
}
