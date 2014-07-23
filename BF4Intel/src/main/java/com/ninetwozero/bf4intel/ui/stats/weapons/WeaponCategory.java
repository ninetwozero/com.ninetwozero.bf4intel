package com.ninetwozero.bf4intel.ui.stats.weapons;

public enum WeaponCategory {
    UNKNOWN(""),
    ASSAULT_RIFLE("WARSAW_ID_P_CAT_ASSAULTRIFLE"),
    CARBINE("WARSAW_ID_P_CAT_CARBINE"),
    DMR("WARSAW_ID_P_CAT_DMR"),
    GADGETS("WARSAW_ID_P_CAT_GADGET"),
    GRENADE("WARSAW_ID_P_CAT_GRENADE"),
    HANDGUN("WARSAW_ID_P_CAT_SIDEARM"),
    KNIVES("WARSAW_ID_P_CAT_KNIFE"),
    LMG("WARSAW_ID_P_CAT_LMG"),
    PDW("WARSAW_ID_P_CAT_PDW"),
    SHOTGUN("WARSAW_ID_P_CAT_SHOTGUN"),
    SNIPER("WARSAW_ID_P_CAT_SNIPER");

    private String categoryId;
    WeaponCategory(final String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public static WeaponCategory from(final String categoryId) {
        for (WeaponCategory category : values()) {
            if (category.getCategoryId().equals(categoryId)) {
                return category;
            }
        }
        return UNKNOWN;
    }
}
