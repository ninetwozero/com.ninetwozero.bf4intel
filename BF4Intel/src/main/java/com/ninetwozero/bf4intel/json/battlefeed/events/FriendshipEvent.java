package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

public class FriendshipEvent extends BaseEvent {
    private String profileId;
    private Profile profile;

    public FriendshipEvent(final EventType eventType, final String profileId, final Profile profile) {
        super(eventType);
        this.profileId = profileId;
        this.profile = profile;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public Profile getProfile() {
        return this.profile;
    }

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.friend_username)).setText(profile.getUsername());
        //TODO: ((ImageView) view.findViewById(R.id.friend_avatar)).setImageUri(profile.getGravatarHash());
    }
}
