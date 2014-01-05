package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.UnknownEvent;

public class UnknownEventLayout implements EventLayout<UnknownEvent> {
    @Override
    public void populateView(final Context context, final View view, final UnknownEvent event) {
    }
}
