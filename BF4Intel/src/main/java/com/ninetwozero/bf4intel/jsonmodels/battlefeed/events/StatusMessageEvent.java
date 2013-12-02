package com.ninetwozero.bf4intel.jsonmodels.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;

public class StatusMessageEvent extends BaseEvent {
    private String message;
    private String preview;

    public StatusMessageEvent(final EventType eventType, final String message, final String preview) {
        super(eventType);
        this.message = message;
        this.preview = preview;
    }

    public String getMessage() {
        return this.message;
    }
    public String getPreview() {
        return this.preview;
    }
    public boolean hasPreview() { return this.preview != null; }

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.content)).setText(message);
        if (hasPreview()) {
            // TODO: Preview title should be resolved from the Internet in some fancy way - use asynctask for thaT?
            ((TextView) view.findViewById(R.id.preview_title)).setText(preview);
            view.findViewById(R.id.wrap_preview).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.wrap_preview).setVisibility(View.GONE);
        }
    }
}
