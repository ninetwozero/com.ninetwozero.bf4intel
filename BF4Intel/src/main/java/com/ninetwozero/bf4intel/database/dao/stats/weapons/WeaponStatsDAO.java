package com.ninetwozero.bf4intel.database.dao.stats.weapons;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.stats.weapons.WeaponStatistics;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierWeaponStatistics")
public class WeaponStatsDAO extends Model {
    public static final String TABLE_NAME = "SoldierWeaponStatistics";

    @Key
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @Key
    @Column("platformId")
    private int platformId;

    @Column("json")
    private WeaponStatistics weaponStats;

    @Column("version")
    private int version;

    public WeaponStatsDAO() {}

    public WeaponStatsDAO(String soldierId, String soldierName, int platformId, WeaponStatistics json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.weaponStats = json;
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

    public WeaponStatistics getWeaponStats() {
        return weaponStats;
    }
}
