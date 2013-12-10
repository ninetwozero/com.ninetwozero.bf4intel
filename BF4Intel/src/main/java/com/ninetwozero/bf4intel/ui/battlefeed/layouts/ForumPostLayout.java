package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.ForumPostEvent;

public class ForumPostLayout implements EventLayout<ForumPostEvent> {
    @Override
    public void populateView(final Context context, final View view, final ForumPostEvent event) {
        ((TextView) view.findViewById(R.id.thread_title)).setText(event.getThreadTitle());
        ((TextView) view.findViewById(R.id.thread_content)).setText(event.getPostBody());
    }
}
