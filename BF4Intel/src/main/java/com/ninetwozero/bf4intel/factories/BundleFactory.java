package com.ninetwozero.bf4intel.factories;

import android.os.Bundle;

import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.resources.Keys;

public class BundleFactory {
    public static Bundle createForProfile(final Profile profile) {
        final Bundle bundle = new Bundle();
        bundle.putString(Keys.Profile.ID, profile.getId());
        bundle.putString(Keys.Profile.USERNAME, profile.getUsername());
        bundle.putString(Keys.Profile.GRAVATAR_HASH, profile.getGravatarHash());
        return bundle;
    }

    public static Bundle createForStats(SummarizedSoldierStats soldierStats) {
        final Bundle bundle = new Bundle();
        bundle.putString(Keys.Soldier.NAME, soldierStats.getPersonaName());
        bundle.putLong(Keys.Soldier.ID, soldierStats.getId());
        bundle.putInt(Keys.Soldier.PLATFORM, soldierStats.getPlatformId());
        return bundle;
    }
}
