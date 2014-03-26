package com.ninetwozero.bf4intel.database.dao;

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

    @Column("personaId")
    private long personaId;

    @Column("personaName")
    private String personaName;
    @Column("tag")
    private String tag;
    @Column("picture")
    private String picture;
    @Column("userId")
    private String userId;

    @Column("rank")
    private int rank;
    @Column("skill")
    private int skill;
    @Column("kills")
    private int killCount;
    @Column("deaths")
    private int deathCount;
    @Column("numWins")
    private int winCount;
    @Column("numLosses")
    private int lossCount;
    @Column("score")
    private long score;
    @Column("timePlayed")
    private int duration;
    @Column("platform")
    private int platformId;
    @Column("game")
    private int gameId;

    public SummarizedSoldierStatsDAO() {}

    public SummarizedSoldierStatsDAO(final SummarizedSoldierStats stats) {
        this.personaId = stats.getPersona().getPersonaId();
        this.personaName = stats.getPersona().getPersonaName();
        this.tag = stats.getPersona().getTag();
        this.picture = stats.getPersona().getPicture();
        this.userId = stats.getPersona().getUserId();
        this.rank = stats.getRank();
        this.skill = stats.getSkill();
        this.killCount = stats.getKillCount();
        this.deathCount = stats.getDeathCount();
        this.winCount = stats.getWinCount();
        this.lossCount = stats.getLossCount();
        this.score = stats.getScore();
        this.duration = stats.getDuration();
        this.platformId = stats.getPlatformId();
        this.gameId = stats.getGameId();
    }

    public long getId() {
        return id;
    }

    public long getPersonaId() {
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

    public String getUserId() {
        return userId;
    }

    public int getRank() {
        return rank;
    }

    public int getSkill() {
        return skill;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLossCount() {
        return lossCount;
    }

    public long getScore() {
        return score;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlatformId() {
        return platformId;
    }

    public int getGameId() {
        return gameId;
    }
}
