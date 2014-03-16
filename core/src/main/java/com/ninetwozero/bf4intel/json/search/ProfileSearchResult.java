package com.ninetwozero.bf4intel.json.search;

import com.google.gson.annotations.SerializedName;
import com.ninetwozero.bf4intel.json.Profile;

import java.util.Map;

public class ProfileSearchResult {
    @SerializedName("personaId")
    private String personaId;
    @SerializedName("personaName")
    private String personaName;
    @SerializedName("namespace")
    private String platform;
    @SerializedName("user")
    private Profile profile;
    @SerializedName("games")
    private Map<Integer, Integer> games;

    public ProfileSearchResult(String personaId, String personaName, String platform, Profile profile, Map<Integer, Integer> games) {
        this.personaId = personaId;
        this.personaName = personaName;
        this.platform = platform;
        this.profile = profile;
        this.games = games;
    }

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

    public Map<Integer, Integer> getGames() {
        return games;
    }
}
