package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.CreatedForumThreadEvent;

public class CreatedForumThreadUiBinder implements EventUiBinder<CreatedForumThreadEvent> {
    @Override
    public void populateView(final Context context, final View view, final CreatedForumThreadEvent event) {
        ((TextView) view.findViewById(R.id.thread_title)).setText(event.getTitle());
        ((TextView) view.findViewById(R.id.thread_content)).setText(event.getContent());
    }
}
