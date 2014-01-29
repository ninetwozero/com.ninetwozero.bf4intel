package com.ninetwozero.bf4intel.interfaces;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public interface EventGenerator {
    BaseEvent generate(final Gson gson, final JsonObject jsonObject);
}
