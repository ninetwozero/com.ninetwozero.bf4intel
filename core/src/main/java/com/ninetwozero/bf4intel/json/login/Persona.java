package com.ninetwozero.bf4intel.json.login;

import com.google.gson.annotations.SerializedName;

public class Persona {
    @SerializedName("personaId")
    private long _id;

    @SerializedName("personaName")
    private String personaName;

    @SerializedName("clanTag")
    private String tag;

    @SerializedName("picture")
    private String picture;

    @SerializedName("namespace")
    private String namespace;

    @SerializedName("userId")
    private String userId;


    public String getUserId() {
        return userId;
    }

    public long getPersonaId() {
        return _id;
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
}
