package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedBlogEvent;

public class CommentedBlogLayout implements EventLayout<CommentedBlogEvent> {
    @Override
    public void populateView(final Context context, final View view, final CommentedBlogEvent event) {
        ((TextView) view.findViewById(R.id.blog_title)).setText(event.getBlogTitle());
        ((TextView) view.findViewById(R.id.comment)).setText(event.getComment());
    }
}
