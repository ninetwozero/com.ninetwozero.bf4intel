package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.BattlePackEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BattlePackItem;

public class BattlePackEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new BattlePackEvent(
            EventType.BATTLEPACK,
            jsonObject.get("nameSID").getAsString(),
            jsonObject.get("packKey").getAsString(),
            gson.fromJson(jsonObject.get("items").getAsJsonArray(), BattlePackItem[].class)
        );
    }
}
