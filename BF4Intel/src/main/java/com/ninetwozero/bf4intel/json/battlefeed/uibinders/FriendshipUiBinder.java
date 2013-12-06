package com.ninetwozero.bf4intel.json.battlefeed.uibinders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.EventUiBinder;
import com.ninetwozero.bf4intel.json.battlefeed.events.FriendshipEvent;

public class FriendshipUiBinder implements EventUiBinder<FriendshipEvent> {
    @Override
    public void populateView(final Context context, final View view, final FriendshipEvent event) {
        ((TextView) view.findViewById(R.id.friend_username)).setText(event.getFriend().getUsername());
        //TODO: ((ImageView) view.findViewById(R.id.friend_avatar)).setImageUri(event.getFriend().getGravatarHash());
    }
}
