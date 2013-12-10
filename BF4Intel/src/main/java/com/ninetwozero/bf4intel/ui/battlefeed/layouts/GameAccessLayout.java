package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameAccessEvent;

public class GameAccessLayout implements EventLayout<GameAccessEvent> {
    @Override
    public void populateView(final Context context, final View view, final GameAccessEvent event) {
        ((TextView) view.findViewById(R.id.game_name)).setText(event.getFullTitle());
    }
}
