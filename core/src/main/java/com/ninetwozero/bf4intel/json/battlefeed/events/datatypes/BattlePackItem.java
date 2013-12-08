package com.ninetwozero.bf4intel.json.battlefeed.events.datatypes;

import com.google.gson.annotations.SerializedName;

public class BattlePackItem {
    @SerializedName("nameSID")
    private String name;

    @SerializedName("parentNameSID")
    private String parentName;

    @SerializedName("category")
    private String category;

    @SerializedName("rarenessLevel")
    private String rarenessLevel;

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public String getCategory() {
        return category;
    }

    public String getRarenessLevel() {
        return rarenessLevel;
    }
}
