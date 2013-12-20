package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class KitUnlock implements Comparable<KitUnlock> {
    @SerializedName("unlockId")
    private String name;
    @SerializedName("guid")
    private String guid;
    @SerializedName("unlockedBy")
    private ScoreCriteria criteria;

    public KitUnlock(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return guid;
    }

    public ScoreCriteria getCriteria() {
        return criteria;
    }

    @Override
    public int compareTo(KitUnlock otherUnlock) {
        final int completion1 = criteria.getCompletion();
        final int completion2 = otherUnlock.getCriteria().getCompletion();

        if ((completion1 < 100 && completion2 < 100) ) {
            if (completion1 > completion2) {
                return -1;
            } else if (completion1 < completion2) {
                return 1;
            }
        } else if(completion1 == completion2) {
            return name.compareToIgnoreCase(otherUnlock.getName());
        } else {
            if (completion1 == 100) {
                return 1;
            } else if (completion2 == 100) {
                return -1;
            }
        }
        return name.compareToIgnoreCase(otherUnlock.getName());
    }
}
