package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.FavoriteServerEvent;

public class FavoriteServerUiBinder implements EventUiBinder<FavoriteServerEvent> {
    @Override
    public void populateView(final Context context, final View view, final FavoriteServerEvent event) {
        ((TextView) view.findViewById(R.id.server_name)).setText(event.getServerName());
    }
}
