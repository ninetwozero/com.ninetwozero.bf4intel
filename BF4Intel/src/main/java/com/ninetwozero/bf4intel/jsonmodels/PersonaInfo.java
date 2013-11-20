package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class PersonaInfo {
    @SerializedName("userId")
    private String userId;

    @SerializedName("personaId")
    private String personaId;

    @SerializedName("tag")
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

    @Override
    public String toString() {
        return "PersonaInfo{" +
            "userId='" + userId + '\'' +
            ", personaId='" + personaId + '\'' +
            ", tag='" + tag + '\'' +
            ", picture='" + picture + '\'' +
            ", platform=" + platform +
            ", game=" + game +
            '}';
    }
}
