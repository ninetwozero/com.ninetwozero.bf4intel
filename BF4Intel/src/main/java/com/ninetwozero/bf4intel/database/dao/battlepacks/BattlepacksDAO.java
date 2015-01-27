package com.ninetwozero.bf4intel.database.dao.battlepacks;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.battlepacks.Battlepacks;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

@Table("Battlepacks")
public class BattlepacksDAO extends Model {
    public static final String TABLE_NAME = "Battlepacks";

    @Key
    @Column("soldierId")
    private String soldierId;

    @Column("soldierName")
    private String soldierName;

    @Key
    @Column("platformId")
    private int platformId;

    @Column("json")
    private Battlepacks battlepacks;

    @Column("version")
    private int version;

    public BattlepacksDAO(){}

    public BattlepacksDAO(String soldierId, String soldierName, int platformId, Battlepacks json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.battlepacks = json;
        this.version = BuildConfig.VERSION_CODE;
    }

    public String getSoldierId() {
        return soldierId;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public int getVersion() {
        return version;
    }

    public int getPlatformId() {
        return platformId;
    }

    public Battlepacks getBattlepacks() {
        return battlepacks;
    }
}
