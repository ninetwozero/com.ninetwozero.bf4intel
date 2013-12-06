package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.StatusMessageEvent;

public class StatusMessageUiBinder implements EventUiBinder<StatusMessageEvent> {
    @Override
    public void populateView(final Context context, final View view, final StatusMessageEvent event) {
        ((TextView) view.findViewById(R.id.content)).setText(event.getMessage());
        if (event.hasPreview()) {
            // TODO: Preview title should be resolved from the Internet in some fancy way - use asynctask for thaT?
            ((TextView) view.findViewById(R.id.preview_title)).setText(event.getPreview());
            view.findViewById(R.id.wrap_preview).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.wrap_preview).setVisibility(View.GONE);
        }
    }
}
