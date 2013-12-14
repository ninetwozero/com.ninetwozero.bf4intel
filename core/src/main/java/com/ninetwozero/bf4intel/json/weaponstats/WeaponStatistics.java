package com.ninetwozero.bf4intel.json.weaponstats;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeaponStatistics {

    @SerializedName("mainWeaponStats")
    private List<Weapon> weaponsList = new ArrayList<Weapon>();

    public List<Weapon> getWeaponsList() {
        return weaponsList;
    }
}
