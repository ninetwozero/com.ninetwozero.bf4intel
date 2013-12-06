package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class ForumPostEvent extends BaseEvent {
    private final String postBody;
    private final String threadTitle;

    public ForumPostEvent(final EventType eventType, final String postBody, final String threadTitle) {
        super(eventType);
        this.postBody = postBody;
        this.threadTitle = threadTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getThreadTitle() {
        return threadTitle;
    }
}
