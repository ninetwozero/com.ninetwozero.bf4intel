package com.ninetwozero.bf4intel.dao;

import com.ninetwozero.bf4intel.json.Profile;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;


@Table("UserProfile")
public class ProfileDAO extends Model {
    public static final String TABLE_NAME = "UserProfile";

    @Key
    @Column("userId")
    private String userId;

    @Column("username")
    private String username;

    @Column("gravatarMd5")
    private String gravatarHash;

    public ProfileDAO() {}

    public ProfileDAO(Profile profile) {
        this.userId = profile.getId();
        this.username = profile.getUsername();
        this.gravatarHash = profile.getGravatarHash();
    }

    public String getId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getGravatarHash() {
        return this.gravatarHash;
    }
}
