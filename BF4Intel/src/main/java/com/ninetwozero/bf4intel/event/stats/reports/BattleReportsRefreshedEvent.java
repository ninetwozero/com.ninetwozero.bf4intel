package com.ninetwozero.bf4intel.event.stats.reports;

import com.ninetwozero.bf4intel.ui.BaseListItem;

import java.util.List;

public class BattleReportsRefreshedEvent {
    private List<BaseListItem> items;

    public BattleReportsRefreshedEvent(List<BaseListItem> items) {
        this.items = items;
    }

    public List<BaseListItem> getItems() {
        return items;
    }
}
