package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class PersonaInfo {
    @SerializedName("userId")
    private String mUserId;

    @SerializedName("personaId")
    private String mPersonaId;

    @SerializedName("tag")
    private String mTag;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("platform")
    private int mPlatform;

    @SerializedName("game")
    private int mGame;

    public PersonaInfo(final String userId, final String personaId, final String tag, final String picture, final int platform, final int game) {
        mUserId = userId;
        mPersonaId = personaId;
        mTag = tag;
        mPicture = picture;
        mPlatform = platform;
        mGame = game;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getPersonaId() {
        return mPersonaId;
    }

    public String getTag() {
        return mTag;
    }

    public String getPicture() {
        return mPicture;
    }

    public int getPlatform() {
        return mPlatform;
    }

    public int getGame() {
        return mGame;
    }
}
