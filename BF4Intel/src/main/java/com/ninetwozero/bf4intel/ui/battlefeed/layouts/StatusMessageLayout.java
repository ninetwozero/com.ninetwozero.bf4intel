package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.StatusMessageEvent;

public class StatusMessageLayout extends BaseLayoutPopulator implements EventLayout<StatusMessageEvent> {
    @Override
    public void populateView(final Context context, final View view, final StatusMessageEvent event) {
        setText(view, R.id.content, event.getMessage());
        if (event.hasPreview()) {
            /*
            TODO:
            We need to figure out a way to resolve the title for the preview in some fancy way
            */
            setText(view, R.id.preview_title, event.getPreview());
            setVisibilty(view, R.id.wrap_preview, View.VISIBLE);
        } else {
            setVisibilty(view, R.id.wrap_preview, View.GONE);
        }
    }
}
