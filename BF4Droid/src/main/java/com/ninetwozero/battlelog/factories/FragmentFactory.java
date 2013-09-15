package com.ninetwozero.battlelog.factories;

import android.app.Fragment;
import android.os.Bundle;

import com.ninetwozero.battlelog.fragments.AccountProfileFragment;
import com.ninetwozero.battlelog.fragments.BattleFeedFragment;
import com.ninetwozero.battlelog.fragments.NewsItemFragment;
import com.ninetwozero.battlelog.fragments.NewsListingFragment;
import com.ninetwozero.battlelog.fragments.PlatoonProfileFragment;
import com.ninetwozero.battlelog.fragments.SlidingMenuFragment;
import com.ninetwozero.battlelog.fragments.SoldierStatsFragment;

public class FragmentFactory {
    public enum Type {
        ACCOUNT_PROFILE,
        BATTLE_FEED,
        PLATOON_PROFILE,
        SOLDIER_STATS,

        NEWS_LISTING,
        NEWS_ITEM,
    }

    public static Fragment get(final Type type) {
        return get(type, null);
    }

    public static Fragment get(final Type type, final Bundle data) {
        switch( type ) {
            case ACCOUNT_PROFILE:
                return AccountProfileFragment.newInstance();
            case BATTLE_FEED:
                return BattleFeedFragment.newInstance();
            case PLATOON_PROFILE:
                return PlatoonProfileFragment.newInstance();
            case SOLDIER_STATS:
                return SoldierStatsFragment.newInstance();
            case NEWS_LISTING:
                return NewsListingFragment.newInstance();
            case NEWS_ITEM:
                return NewsItemFragment.newInstance(data);
            default:
                throw new TypeNotPresentException("No fragment corresponds to: " + type, null);
        }
    }
}
