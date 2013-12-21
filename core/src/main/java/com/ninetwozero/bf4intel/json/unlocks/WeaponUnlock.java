package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class WeaponUnlock implements Comparable<WeaponUnlock> {
    @SerializedName("guid")
    private String guid;
    @SerializedName("slug")
    private String slug;
    @SerializedName("unlockedBy")
    private UnlockCriteria criteria;

    public WeaponUnlock(final String slug) {
        this.slug = slug;
    }

    public String getGuid() {
        return guid;
    }

    public String getSlug() {
        return slug;
    }

    public UnlockCriteria getCriteria() {
        return criteria;
    }

    @Override
    public int compareTo(final WeaponUnlock otherUnlock) {
        return criteria.compareTo(otherUnlock.getCriteria());
    }
}
