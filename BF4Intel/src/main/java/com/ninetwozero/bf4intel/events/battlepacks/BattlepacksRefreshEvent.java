package com.ninetwozero.bf4intel.events.battlepacks;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class BattlepacksRefreshEvent extends RefreshCompletedEvent {
    public BattlepacksRefreshEvent(boolean result) {
        super(result);
    }
}
