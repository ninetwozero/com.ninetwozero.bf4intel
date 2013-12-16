package com.ninetwozero.bf4intel.json.search;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;

public class ProfileSearchResult {
    @SerializedName("personaId")
    private String personaId;
    @SerializedName("personaName")
    private String personaName;
    @SerializedName("namespace")
    private String platform;
    @SerializedName("user")
    private Profile profile;

    public String getPersonaId() {
        return personaId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public String getPlatform() {
        return platform;
    }

    public Profile getProfile() {
        return profile;
    }
}
