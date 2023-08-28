package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class FriendshipEvent extends BaseEvent {
    @SerializedName("friendUserId")
    private String friendId;
    @SerializedName("friendUser")
    private Profile friend;

    public String getFriendId() {
        return this.friendId;
    }

    public Profile getFriend() {
        return this.friend;
    }
}
