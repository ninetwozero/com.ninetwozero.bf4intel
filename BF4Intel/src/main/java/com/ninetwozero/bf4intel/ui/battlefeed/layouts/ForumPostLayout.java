package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.ForumPostEvent;

public class ForumPostLayout extends BaseLayoutPopulator implements EventLayout<ForumPostEvent> {
    @Override
    public void populateView(final Context context, final View view, final ForumPostEvent event) {
        setText(view, R.id.thread_title, event.getThreadTitle());
        setText(view, R.id.thread_content, event.getPostBody());
    }
}
