package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.CreatedForumThreadEvent;

public class CreatedForumThreadLayout extends BaseLayoutPopulator implements EventLayout<CreatedForumThreadEvent> {
    @Override
    public void populateView(final Context context, final View view, final CreatedForumThreadEvent event) {
        setText(view, R.id.thread_title, event.getTitle());
        setText(view, R.id.thread_content, event.getContent());
    }
}
