package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;
import com.ninetwozero.bf4intel.json.battlefeed.events.FriendshipEvent;

public class FriendshipEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new FriendshipEvent(
            EventType.BECAME_FRIENDS,
            jsonObject.get("friendUserId").getAsString(),
            gson.fromJson(jsonObject.get("friendUser"), Profile.class)
        );
    }
}
