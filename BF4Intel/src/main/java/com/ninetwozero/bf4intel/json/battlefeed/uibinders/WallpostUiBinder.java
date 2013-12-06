package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.WallpostEvent;

public class WallpostUiBinder implements EventUiBinder<WallpostEvent> {
    @Override
    public void populateView(final Context context, final View view, final WallpostEvent event) {
        ((TextView) view.findViewById(R.id.username2)).setText(event.getSender().getUsername());
        // TODO: ((ImageView) view.findViewById(R.id.avatar2)).setImageUri(event.getSender().getGravatarHash());
        ((TextView) view.findViewById(R.id.content)).setText(event.getMessage());
    }
}
