package com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.SharedGameEvent;

public class SharedGameEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        // TODO: This is when we share awards and such
        Log.d("YOLO", "jsonObject => " + jsonObject);
        return new SharedGameEvent(EventType.SHAREDGAMEEVENT);
    }
}
