package com.ninetwozero.bf4intel.services.battlepacks;

import com.ninetwozero.bf4intel.database.dao.battlepacks.BattlepacksDAO;
import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;
import com.ninetwozero.bf4intel.events.battlepacks.BattlepacksRefreshEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.battlepacks.Battlepacks;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.services.BaseDaoService;

import java.net.URL;

public class BattlepacksService extends BaseDaoService<BattlepacksDAO,RefreshCompletedEvent> {
    @Override
    protected BattlepacksDAO parseJsonIntoDao(String json) {
        Battlepacks battlepacks = fromJson(json, Battlepacks.class);
        return new BattlepacksDAO(
                soldier.getString(Keys.Soldier.ID),
                soldier.getString(Keys.Soldier.NAME),
                soldier.getInt(Keys.Soldier.PLATFORM),
                battlepacks);
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildBattlepacksURL(
                soldier.getString(Keys.Soldier.ID, ""),
                soldier.getInt(Keys.Soldier.PLATFORM, 0)
        );
    }

    @Override
    protected BattlepacksRefreshEvent getEventToBroadcast(boolean result) {
        return new BattlepacksRefreshEvent(result);
    }
}
