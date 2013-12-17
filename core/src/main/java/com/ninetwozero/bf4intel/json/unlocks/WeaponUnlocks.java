package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponUnlocks {
    @SerializedName("personaId")
    private long personaId;
    @SerializedName("personaName")
    private String personaName;
    /*@SerializedName("allMissions")
    private Map<String, Assignment> assignments = new HashMap<String, Assignment>();
    */
    @SerializedName("weaponsByCategory")
    private Map<String, List<WeaponUnlock>> weaponUnlocks = new HashMap<String, List<WeaponUnlock>>();
}