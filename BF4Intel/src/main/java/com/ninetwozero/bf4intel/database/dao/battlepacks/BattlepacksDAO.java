package com.ninetwozero.bf4intel.database.dao.battlepacks;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.json.battlepacks.Battlepacks;

import se.emilsjolander.sprinkles.Model;

public class BattlepacksDAO extends Model {
    public static final String TABLE_NAME = "Battlepacks";

    private final String soldierId;
    private final String soldierName;
    private final int version;
    private final int platformId;
    private final Battlepacks battlepacks;

    public BattlepacksDAO(String soldierId, String soldierName, int platformId, Battlepacks json) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.platformId = platformId;
        this.battlepacks = json;
        this.version = BuildConfig.VERSION_CODE;
    }

    public static String getTableName() {
        return TABLE_NAME;
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
