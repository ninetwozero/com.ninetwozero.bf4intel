package com.ninetwozero.bf4intel.json.soldieroverview;

import com.google.gson.annotations.SerializedName;

public class PersonaInfo {
    @SerializedName("userId")
    private String userId;

    @SerializedName("personaId")
    private String personaId;

    @SerializedName("personaName")
    private String personaName;

    @SerializedName("clanTag")
    private String tag;

    @SerializedName("picture")
    private String picture;

    @SerializedName("platform")
    private int platform;

    @SerializedName("game")
    private int game;

    public String getUserId() {
        return userId;
    }

    public String getPersonaId() {
        return personaId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public String getTag() {
        return tag;
    }

    public String getPicture() {
        return picture;
    }

    public int getPlatform() {
        return platform;
    }

    public int getGame() {
        return game;
    }
}
