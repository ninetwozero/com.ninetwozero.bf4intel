package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameReportEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BestInCategory;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.PersonalHighlight;
import com.ninetwozero.bf4intel.json.battlereports.Team;

import java.util.HashMap;
import java.util.Map;

public class GameReportEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        final Map<String, Team> teams = gson.fromJson(
            jsonObject.get("teamScores"),
            new TypeToken<HashMap<String, Team>>(){}.getType()
        );
        return new GameReportEvent(
            EventType.GAMEREPORT,
            jsonObject.get("serverName").getAsString(),
            jsonObject.get("map").getAsString(),
            jsonObject.get("gameMode").getAsInt(),
            jsonObject.get("position").getAsInt(),
            jsonObject.get("kills").getAsInt(),
            jsonObject.get("kdr").getAsDouble(),
            jsonObject.get("skill").getAsInt(),
            jsonObject.get("duration").getAsInt(),
            jsonObject.get("score").getAsInt(),
            jsonObject.get("spm").getAsInt(),
            teams,
            jsonObject.get("playerTeam").getAsInt(),
            jsonObject.get("winTeam").getAsInt(),
            gson.fromJson(jsonObject.get("bestVehicle"), BestInCategory.class),
            gson.fromJson(jsonObject.get("bestWeapon"), BestInCategory.class),
            gson.fromJson(jsonObject.get("personalPrize"), PersonalHighlight.class)
        );
    }
}
