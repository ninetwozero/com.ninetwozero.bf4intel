package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedBlogEvent;

public class CommentedBlogUiBinder implements EventUiBinder<CommentedBlogEvent> {
    @Override
    public void populateView(final Context context, final View view, final CommentedBlogEvent event) {
        ((TextView) view.findViewById(R.id.blog_title)).setText(event.getBlogTitle());
        ((TextView) view.findViewById(R.id.comment)).setText(event.getComment());
    }
}
