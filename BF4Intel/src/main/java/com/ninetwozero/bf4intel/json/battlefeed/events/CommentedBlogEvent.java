package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CommentedBlogEvent extends BaseEvent {
    private final String blogTitle;
    private final String comment;

    public CommentedBlogEvent(final EventType eventType, final String blogTitle, final String comment) {
        super(eventType);
        this.blogTitle = blogTitle;
        this.comment = comment;
    }

    public String getBlogTitle() {
        return this.blogTitle;
    }

    public String getComment() {
        return this.comment;
    }

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.blog_title)).setText(blogTitle);
        ((TextView) view.findViewById(R.id.comment)).setText(comment);
    }
}