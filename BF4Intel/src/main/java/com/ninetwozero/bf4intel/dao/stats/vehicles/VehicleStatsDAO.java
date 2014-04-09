package com.ninetwozero.bf4intel.dao.stats.vehicles;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.stats.vehicles.GroupedVehicleStatsContainer;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("SoldierVehicleStatistics")
public class VehicleStatsDAO extends Model {
    public static final String TABLE_NAME = "SoldierVehicleStatistics";

    @Key
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @Key
    @Column("platformId")
    private int platformId;

    @Column("json")
    private GroupedVehicleStatsContainer vehicleStats;

    @Column("version")
    private int version;

    public VehicleStatsDAO() {}

    public VehicleStatsDAO(String soldierId, String soldierName, int platformId, GroupedVehicleStatsContainer json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.vehicleStats = json;
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

    public GroupedVehicleStatsContainer getVehicleStats() {
        return vehicleStats;
    }
}
