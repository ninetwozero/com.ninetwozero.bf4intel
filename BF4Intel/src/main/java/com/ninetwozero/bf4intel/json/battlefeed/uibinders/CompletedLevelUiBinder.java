package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.CompletedLevelEvent;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;

public class CompletedLevelUiBinder implements EventUiBinder<CompletedLevelEvent> {
    @Override
    public void populateView(final Context context, final View view, final CompletedLevelEvent event) {
        ((TextView) view.findViewById(R.id.level)).setText(LevelStringMap.get(event.getLevelName()));
        ((TextView) view.findViewById(R.id.difficulty)).setText(event.getDifficulty());
        if (event.hasPartner()) {
            ((TextView) view.findViewById(R.id.partner)).setText(event.getPartner().getUsername());
            view.findViewById(R.id.wrap_partner).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.wrap_partner).setVisibility(View.GONE);
        }
    }
}
