package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class CriteriaAward {
    @SerializedName("stringID")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("awardRealm")
    private String awardRealm;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAwardRealm() {
        return awardRealm;
    }
}
