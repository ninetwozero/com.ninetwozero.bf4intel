package com.ninetwozero.bf4intel.database.dao.stats.details;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.stats.details.DetailedStatsContainer;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.PrimaryKey;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierDetailedStatistics")
public class DetailedStatsDAO extends Model {
    public static final String TABLE_NAME = "SoldierDetailedStatistics";

    @PrimaryKey
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @PrimaryKey
    @Column("platformId")
    private int platformId;

    @Column("json")
    private DetailedStatsContainer detailedStats;

    @Column("version")
    private int version;

    public DetailedStatsDAO() {}

    public DetailedStatsDAO(String soldierId, String soldierName, int platformId, DetailedStatsContainer json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.detailedStats = json;
        this.version = BuildConfig.VERSION_CODE;
    }

    public String getSoldierId() {
        return soldierId;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public int getPlatformId() {
        return platformId;
    }

    public DetailedStatsContainer getDetailedStats() {
        return detailedStats;
    }
}
