package com.ninetwozero.bf4intel.dao.awards;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.awards.SortedAwardContainer;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierAwards")
public class AwardsDAO extends Model {
    public static final String TABLE_NAME = "SoldierAwards";

    @Key
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @Key
    @Column("platformId")
    private int platformId;

    @Column("json")
    private SortedAwardContainer sortedAwardContainer;

    @Column("version")
    private int version;

    public AwardsDAO() {}

    public AwardsDAO(String soldierId, String soldierName, int platformId, SortedAwardContainer sortedAwardContainer) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.sortedAwardContainer = sortedAwardContainer;
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

    public SortedAwardContainer getSortedAwardContainer() {
        return sortedAwardContainer;
    }
}
