package com.ninetwozero.bf4intel.factories;

import android.os.Bundle;

import com.ninetwozero.bf4intel.json.Profile;
import com.ninetwozero.bf4intel.database.dao.login.SummarizedSoldierStatsDAO;
import com.ninetwozero.bf4intel.resources.Keys;

public class BundleFactory {
    public static Bundle createForProfile(final Profile profile) {
        final Bundle bundle = new Bundle();
        bundle.putString(Keys.Profile.ID, profile.getId());
        bundle.putString(Keys.Profile.USERNAME, profile.getUsername());
        bundle.putString(Keys.Profile.GRAVATAR_HASH, profile.getGravatarHash());
        return bundle;
    }

    public static Bundle createForStats(SummarizedSoldierStatsDAO soldierStats) {
        final Bundle bundle = new Bundle();
        bundle.putString(Keys.Soldier.NAME, soldierStats.getSoldierName());
        bundle.putString(Keys.Soldier.ID, soldierStats.getSoldierId());
        bundle.putString(Keys.Profile.ID, soldierStats.getUserId());
        bundle.putInt(Keys.Soldier.PLATFORM, soldierStats.getPlatformId());
        return bundle;
    }
}
