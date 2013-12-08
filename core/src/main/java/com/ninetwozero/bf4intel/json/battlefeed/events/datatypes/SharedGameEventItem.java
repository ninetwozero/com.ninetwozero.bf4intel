package com.ninetwozero.bf4intel.json.battlefeed.events.datatypes;

import com.google.gson.annotations.SerializedName;

public class SharedGameEventItem {
    @SerializedName("nameSID")
    private String name;

    @SerializedName("descriptionSID")
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
