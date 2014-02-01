package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedBlogEvent;

public class CommentedBlogLayout extends BaseLayoutPopulator implements EventLayout<CommentedBlogEvent> {
    @Override
    public void populateView(final Context context, final View view, final CommentedBlogEvent event) {
        setText(view, R.id.blog_title, event.getBlogTitle());
        setText(view, R.id.comment, event.getComment());
    }
}
