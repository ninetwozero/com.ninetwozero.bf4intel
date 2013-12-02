package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
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

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.thread_title)).setText(title);
        ((TextView) view.findViewById(R.id.thread_content)).setText(content);
    }
}
