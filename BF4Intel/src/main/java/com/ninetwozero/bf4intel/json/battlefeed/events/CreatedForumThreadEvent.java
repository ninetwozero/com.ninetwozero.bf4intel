package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CreatedForumThreadEvent extends BaseEvent {
    private final String title;
    private final String content;

    public CreatedForumThreadEvent(final EventType eventType, final String title, final String content) {
        super(eventType);
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
