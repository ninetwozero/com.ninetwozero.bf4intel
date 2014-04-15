package com.ninetwozero.bf4intel.dao.soldieroverview;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierOverview")
public class SoldierOverviewDAO extends Model {
    public static final String TABLE_NAME = "SoldierOverview";

    @Key
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @Key
    @Column("platformId")
    private int platformId;

    @Column("json")
    private SoldierOverview soldierOverview;

    @Column("version")
    private int version;

    public SoldierOverviewDAO() {}

    public SoldierOverviewDAO(String soldierId, String soldierName, int platformId, SoldierOverview json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.soldierOverview = json;
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

    public SoldierOverview getSoldierOverview() {
        return soldierOverview;
    }
}
