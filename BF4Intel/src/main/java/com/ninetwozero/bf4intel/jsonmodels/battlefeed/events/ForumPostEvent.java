package com.ninetwozero.bf4intel.jsonmodels.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;

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

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.thread_title)).setText(threadTitle);
        ((TextView) view.findViewById(R.id.thread_content)).setText(postBody);
    }
}
