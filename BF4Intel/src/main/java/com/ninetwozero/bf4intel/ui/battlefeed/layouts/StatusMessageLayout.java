package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.StatusMessageEvent;

public class StatusMessageLayout implements EventLayout<StatusMessageEvent> {
    @Override
    public void populateView(final Context context, final View view, final StatusMessageEvent event) {
        ((TextView) view.findViewById(R.id.content)).setText(event.getMessage());
        if (event.hasPreview()) {
            /*
            TODO:
            We need to figure out a way to resolve the title for the preview in some fancy way
            */
            ((TextView) view.findViewById(R.id.preview_title)).setText(event.getPreview());
            view.findViewById(R.id.wrap_preview).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.wrap_preview).setVisibility(View.GONE);
        }
    }
}
