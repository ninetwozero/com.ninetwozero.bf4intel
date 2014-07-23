package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RibbonAward implements Serializable {

    @SerializedName("descriptionID")
    private String descriptionId;
    @SerializedName("stringID")
    private String uniqueName;

    public String getDescriptionId() {
        return descriptionId;
    }

    public String getUniqueName() {
        return uniqueName;
    }
}
