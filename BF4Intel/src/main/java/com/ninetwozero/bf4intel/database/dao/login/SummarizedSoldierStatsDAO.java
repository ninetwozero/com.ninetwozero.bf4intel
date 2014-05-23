package com.ninetwozero.bf4intel.database.dao.login;

import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;


@Table("UserSoldier")
public class SummarizedSoldierStatsDAO extends Model {
    public static final String TABLE_NAME = "UserSoldier";

    @AutoIncrementPrimaryKey
    @Column("rowId")
    private long id;

    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;
    @Column("tag")
    private String tag;
    @Column("picture")
    private String picture;
    @Column("userId")
    private String userId;

    @Column("rank")
    private int rank;
    @Column("platform")
    private int platformId;
    @Column("game")
    private int gameId;

    public SummarizedSoldierStatsDAO() {}

    public SummarizedSoldierStatsDAO(final SummarizedSoldierStats stats) {
        this.soldierId = String.valueOf(stats.getPersona().getPersonaId());
        this.soldierName = stats.getPersona().getPersonaName();
        this.tag = stats.getPersona().getTag();
        this.picture = stats.getPersona().getPicture();
        this.userId = stats.getPersona().getUserId();
        this.rank = stats.getRank();
        this.platformId = stats.getPlatformId();
        this.gameId = stats.getGameId();
    }

    public long getId() {
        return id;
    }

    public String getSoldierId() {
        return soldierId;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public String getTag() {
        return tag;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserId() {
        return userId;
    }

    public int getRank() {
        return rank;
    }

    public int getPlatformId() {
        return platformId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
