package com.ninetwozero.bf4intel.json.battlepacks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Battlepacks {

    //@SerializedName("upcomingRankPacks")
    private List<RankPack> upcomingRanks;
    @SerializedName("upcomingWeaponPacks")
    private List<WeaponPack> weaponPackList;
    //@SerializedName("suggestions")


    public List<WeaponPack> getWeaponPackList() {
        return weaponPackList;
    }
}
