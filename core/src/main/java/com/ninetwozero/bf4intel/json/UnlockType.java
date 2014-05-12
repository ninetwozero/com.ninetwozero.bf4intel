package com.ninetwozero.bf4intel.json;

public enum UnlockType {
    UNKNOWN(""),
    WEAPON("weaponUnlock"),
    WEAPON_ADDON("weaponAddonUnlock"),
    VEHICLE_ADDON("vehicleAddonUnlock"),
    KIT_ITEM("kitItemUnlock"),
    APPEARANCE("appearanceUnlock"),
    SOLDIER_SPECIALIZATION("soldierSpecializationUnlock"),
    DOGTAG("dogTagUnlock");

    private String jsonKey;
    UnlockType(final String jsonKey) {
        this.jsonKey = jsonKey;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public static UnlockType from(String jsonKey) {
        for (UnlockType type : values()) {
            if (type.getJsonKey().equals(jsonKey)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
