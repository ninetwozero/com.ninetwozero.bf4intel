package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

public class FriendshipEvent extends BaseEvent {
    private String friendId;
    private Profile friend;

    public FriendshipEvent(final EventType eventType, final String friendId, final Profile friend) {
        super(eventType);
        this.friendId = friendId;
        this.friend = friend;
    }

    public String getFriendId() {
        return this.friendId;
    }

    public Profile getFriend() {
        return this.friend;
    }
}
