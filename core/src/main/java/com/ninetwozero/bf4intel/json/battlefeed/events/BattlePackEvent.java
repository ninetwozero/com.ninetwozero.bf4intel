package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.datatypes.BattlePackItem;

import java.util.List;

public class BattlePackEvent extends BaseEvent {
    @SerializedName("nameSID")
    private String name;
    @SerializedName("packKey")
    private String packKey;
    @SerializedName("items")
    private List<BattlePackItem> items;

    public String getName() {
        return name;
    }

    public String getPackKey() {
        return packKey;
    }

    public List<BattlePackItem> getItems() {
        return items;
    }

    public static String fetchPackType(final String name) {
        if (name.contains("WEAPON")) {
            return "WEAPON";
        } else {
            return name.substring(name.lastIndexOf('_')+1);
        }
    }
}
