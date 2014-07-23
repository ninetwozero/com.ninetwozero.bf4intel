package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.WallpostEvent;

public class WallpostLayout extends BaseLayoutPopulator implements EventLayout<WallpostEvent> {
    @Override
    public void populateView(final Context context, final View view, final WallpostEvent event) {
        setText(view, R.id.username2, event.getSender().getUsername());
        loadImage(view, R.id.avatar2, UrlFactory.buildGravatarUrl(event.getSender().getGravatarHash()));
        setText(view, R.id.content, event.getMessage());
    }
}
