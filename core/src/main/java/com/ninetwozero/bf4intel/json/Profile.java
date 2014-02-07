package com.ninetwozero.bf4intel.json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Profile implements Serializable {
    @SerializedName("userId")
    private String _id;

    @SerializedName("username")
    private String username;

    @SerializedName("gravatarMd5")
    private String gravatarHash;

    public String getId() {
        return this._id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getGravatarHash() {
        return this.gravatarHash;
    }
}
