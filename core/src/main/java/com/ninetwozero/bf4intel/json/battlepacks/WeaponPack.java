package com.ninetwozero.bf4intel.json.battlepacks;

import com.google.gson.annotations.SerializedName;

public class WeaponPack {
    @SerializedName("Weapon")
    private WeaponBattlepackInfo weaponInfo;

    public WeaponBattlepackInfo getWeaponInfo() {
        return weaponInfo;
    }

    public static class WeaponBattlepackInfo {
        @SerializedName("nameSID")
        private String uniqueId;
        @SerializedName("actualValue")
        private int currentValue;
        @SerializedName("valueNeeded")
        private int requiredValue;

        public String getUniqueId() {
            return uniqueId;
        }

        public int getCurrentValue() {
            return currentValue;
        }

        public int getRequiredValue() {
            return requiredValue;
        }
    }
}
