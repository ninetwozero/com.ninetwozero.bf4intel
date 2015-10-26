package com.ninetwozero.bf4intel.database.dao.battlepacks;

import android.content.Context;

import com.ninetwozero.bf4intel.BuildConfig;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.battlepacks.Battlepacks;
import com.ninetwozero.bf4intel.json.battlepacks.WeaponPack;
import com.ninetwozero.bf4intel.ui.BaseListItem;
import com.ninetwozero.bf4intel.ui.SimpleListHeader;
import com.ninetwozero.bf4intel.ui.battlepacks.WeaponBattlepackItem;

import java.util.ArrayList;
import java.util.List;

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

    public List<BaseListItem> getBattlepacks(Context context) {
        List<BaseListItem> listItems = new ArrayList<>();
        if(battlepacks.getWeaponPackList() != null && battlepacks.getWeaponPackList().size() > 0) {
            listItems.add(new SimpleListHeader(R.string.upcoming_rank_battlepacks));
            listItems.addAll(buildWeaponPacks(battlepacks.getWeaponPackList(), context));
        }

        return listItems;
    }

    private List<BaseListItem> buildWeaponPacks(List<WeaponPack> weaponPackList, Context context) {
        List<BaseListItem> weaponPacks = new ArrayList<>();
        for (WeaponPack pack : weaponPackList) {
            weaponPacks.add(new WeaponBattlepackItem(pack, context));
        }
        return weaponPacks;
    }
}
