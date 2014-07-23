package com.ninetwozero.bf4intel.json.stats.weapons;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeaponStatistics {

    @SerializedName("mainWeaponStats")
    private List<Weapon> weaponsList = new ArrayList<Weapon>();

    public List<Weapon> getWeaponsList() {
        return weaponsList;
    }

    public void setWeaponsList(List<Weapon> weaponsList) {
        this.weaponsList = new ArrayList<Weapon>(weaponsList);
    }
}
