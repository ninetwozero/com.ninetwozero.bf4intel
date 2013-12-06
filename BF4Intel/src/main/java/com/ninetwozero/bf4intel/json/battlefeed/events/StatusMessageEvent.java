package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

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
}
