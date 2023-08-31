package com.ninetwozero.bf4intel.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.ninetwozero.bf4intel.json.soldieroverview.SoldierOverview;

@Entity(tableName = "soldier_overview", primaryKeys = {"soldierId", "platformId"})
public class SoldierOverviewEntity {

    @ColumnInfo(name="soldierId")
    private String soldierId;

    @ColumnInfo(name="soldierName")
    private String soldierName;

    @ColumnInfo(name="platformId")
    private int platformId;

    @ColumnInfo(name="json")
    private SoldierOverview soldierOverviewJson;

    @ColumnInfo(name="version")
    private int version;

    public SoldierOverviewEntity() {}

    public SoldierOverviewEntity(String soldierId, String soldierName, int platformId, SoldierOverview json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.soldierOverviewJson = json;
    }

    public String getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(String soldierId) {
        this.soldierId = soldierId;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public void setSoldierName(String soldierName) {
        this.soldierName = soldierName;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public SoldierOverview getSoldierOverviewJson() {
        return soldierOverviewJson;
    }

    public void setSoldierOverviewJson(SoldierOverview soldierOverviewJson) {
        this.soldierOverviewJson = soldierOverviewJson;
    }
}
