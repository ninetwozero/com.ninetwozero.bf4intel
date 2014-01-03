package com.ninetwozero.bf4intel.ui.battlefeed.layouts;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.interfaces.EventLayout;
import com.ninetwozero.bf4intel.json.battlefeed.events.FriendshipEvent;
import com.squareup.picasso.Picasso;

public class FriendshipLayout implements EventLayout<FriendshipEvent> {
    @Override
    public void populateView(final Context context, final View view, final FriendshipEvent event) {
        ((TextView) view.findViewById(R.id.friend_username)).setText(event.getFriend().getUsername());
        Picasso.with(context).load(
            UrlFactory.buildGravatarUrl(event.getFriend().getGravatarHash())
        ).into((ImageView) view.findViewById(R.id.friend_avatar));
    }
}
