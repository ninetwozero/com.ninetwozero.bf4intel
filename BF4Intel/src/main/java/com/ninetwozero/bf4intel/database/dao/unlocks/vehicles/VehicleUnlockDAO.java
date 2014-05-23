package com.ninetwozero.bf4intel.database.dao.unlocks.vehicles;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.unlocks.vehicles.SortedVehicleUnlocks;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.PrimaryKey;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierVehicleUnlocks")
public class VehicleUnlockDAO extends Model {
    public static final String TABLE_NAME = "SoldierVehicleUnlocks";

    @PrimaryKey
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @PrimaryKey
    @Column("platformId")
    private int platformId;

    @Column("json")
    private SortedVehicleUnlocks vehicleUnlocks;

    @Column("version")
    private int version;

    public VehicleUnlockDAO() {}

    public VehicleUnlockDAO(String soldierId, String soldierName, int platformId, SortedVehicleUnlocks json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.vehicleUnlocks = json;
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

    public SortedVehicleUnlocks getVehicleUnlocks() {
        return vehicleUnlocks;
    }
}
