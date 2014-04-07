package com.ninetwozero.bf4intel.dao.unlocks.kits;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.unlocks.kits.SortedKitUnlocks;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.PrimaryKey;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierKitUnlocks")
public class KitUnlockDAO extends Model {
    public static final String TABLE_NAME = "SoldierKitUnlocks";

    @PrimaryKey
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @PrimaryKey
    @Column("platformId")
    private int platformId;

    @Column("json")
    private SortedKitUnlocks kitUnlocks;

    @Column("version")
    private int version;

    public KitUnlockDAO() {}

    public KitUnlockDAO(String soldierId, String soldierName, int platformId, SortedKitUnlocks json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.kitUnlocks = json;
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

    public SortedKitUnlocks getKitUnlocks() {
        return kitUnlocks;
    }
}
