package com.ninetwozero.bf4intel.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ninetwozero.bf4intel.json.Profile;


@Entity(tableName="user_profile")
public class ProfileEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="userId")
    private String userId;

    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="gravatarMd5")
    private String gravatarHash;

    public ProfileEntity() {}

    public ProfileEntity(Profile profile) {
        this.userId = profile.getId();
        this.username = profile.getUsername();
        this.gravatarHash = profile.getGravatarHash();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGravatarHash() {
        return this.gravatarHash;
    }

    public void setGravatarHash(String gravatarHash) {
        this.gravatarHash = gravatarHash;
    }
}