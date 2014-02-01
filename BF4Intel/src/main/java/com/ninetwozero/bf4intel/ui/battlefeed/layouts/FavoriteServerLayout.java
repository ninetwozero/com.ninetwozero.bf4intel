package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.FavoriteServerEvent;

public class FavoriteServerLayout extends BaseLayoutPopulator implements EventLayout<FavoriteServerEvent> {
    @Override
    public void populateView(final Context context, final View view, final FavoriteServerEvent event) {
        setText(view, R.id.server_name, event.getServerName());
    }
}
