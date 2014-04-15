package com.ninetwozero.bf4intel.dao.unlocks.weapons;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.unlocks.weapons.SortedWeaponUnlocks;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierWeaponUnlocks")
public class WeaponUnlockDAO extends Model {
    public static final String TABLE_NAME = "SoldierWeaponUnlocks";

    @Key
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @Key
    @Column("platformId")
    private int platformId;

    @Column("json")
    private SortedWeaponUnlocks weaponUnlock;

    @Column("version")
    private int version;

    public WeaponUnlockDAO() {}

    public WeaponUnlockDAO(String soldierId, String soldierName, int platformId, SortedWeaponUnlocks json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.weaponUnlock = json;
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

    public SortedWeaponUnlocks getWeaponUnlock() {
        return weaponUnlock;
    }
}
