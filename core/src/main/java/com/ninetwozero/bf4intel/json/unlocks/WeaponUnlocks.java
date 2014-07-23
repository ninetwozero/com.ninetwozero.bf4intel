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
    @SerializedName("weaponsByCategory")
    private Map<String, List<WeaponUnlockContainer>> unlockMap = new HashMap<String, List<WeaponUnlockContainer>>();

    public long getPersonaId() {
        return personaId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public Map<String, List<WeaponUnlockContainer>> getUnlockMap() {
        return unlockMap;
    }
}
