package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLayoutPopulator;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.FriendshipEvent;

public class FriendshipLayout extends BaseLayoutPopulator implements EventLayout<FriendshipEvent> {
    @Override
    public void populateView(final Context context, final View view, final FriendshipEvent event) {
        setText(view, R.id.friend_username, event.getFriend().getUsername());
        loadImage(view, R.id.friend_avatar, UrlFactory.buildGravatarUrl(event.getFriend().getGravatarHash()));
    }
}
