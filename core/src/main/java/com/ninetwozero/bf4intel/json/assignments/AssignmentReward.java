package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.UnlockType;

import java.io.Serializable;

public class AssignmentReward implements Serializable {
    @SerializedName("nameSID")
    private String name;
    @SerializedName("descriptionSID")
    private String description;
    @SerializedName("index")
    private int imageIndex;
    @SerializedName("isAdvanced")
    private boolean advanced;
    @SerializedName("unlockTypeString")
    private String unlockTypeString;
    @SerializedName("unlockType")
    private UnlockType unlockType;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public String getUnlockTypeString() {
        return unlockTypeString;
    }

    public UnlockType getUnlockType() {
        return unlockType;
    }

    public void setUnlockType(UnlockType unlockType) {
        this.unlockType = unlockType;
    }
}
