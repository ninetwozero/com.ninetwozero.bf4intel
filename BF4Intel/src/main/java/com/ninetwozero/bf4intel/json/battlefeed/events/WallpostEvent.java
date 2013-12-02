package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

public class WallpostEvent extends BaseEvent {
    private Profile sender;
    private String message;

    public WallpostEvent(final EventType eventType, final Profile sender, final String message) {
        super(eventType);
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public Profile getSender() {
        return this.sender;
    }

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.username2)).setText(sender.getUsername());
        // TODO: ((ImageView) view.findViewById(R.id.avatar2)).setImageUri(sender.getGravatarHash());
    }
}
