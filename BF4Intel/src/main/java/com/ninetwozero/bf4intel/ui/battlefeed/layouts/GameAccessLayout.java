package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameAccessEvent;

public class GameAccessLayout extends BaseLayoutPopulator implements EventLayout<GameAccessEvent> {
    @Override
    public void populateView(final Context context, final View view, final GameAccessEvent event) {
        setText(view, R.id.game_name, event.getFullTitle());
    }
}
