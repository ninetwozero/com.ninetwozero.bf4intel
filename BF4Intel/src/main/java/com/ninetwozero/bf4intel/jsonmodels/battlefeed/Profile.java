package com.ninetwozero.bf4intel.jsonmodels.battlefeed;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("userId")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("gravatarMd5")
    private String gravatarHash;

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getGravatarHash() {
        return this.gravatarHash;
    }
}
