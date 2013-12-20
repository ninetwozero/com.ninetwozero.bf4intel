package com.ninetwozero.bf4intel.factories;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.ui.battlefeed.BattleFeedFragment;
import com.ninetwozero.bf4intel.ui.battlefeed.BattleFeedPostingFragment;
import com.ninetwozero.bf4intel.ui.battlereport.BattleReportFragment;
import com.ninetwozero.bf4intel.ui.battlereport.BattleReportListingFragment;
import com.ninetwozero.bf4intel.ui.fragments.*;
import com.ninetwozero.bf4intel.ui.search.ProfileSearchFragment;
import com.ninetwozero.bf4intel.ui.stats.vehicles.VehicleStatsFragment;
import com.ninetwozero.bf4intel.ui.stats.weapons.WeaponStatsFragment;

public class FragmentFactory {
    public enum Type {
        ACCOUNT_PROFILE,

        SOLDIER_OVERVIEW,
        SOLDIER_STATS,
        WEAPON_STATS,
        VEHICLE_STATS,

        SOLDIER_UNLOCKS,
        SOLDIER_ASSIGNMENTS,

        BATTLE_FEED,
        BATTLE_FEED_POSTING,

        PLATOON_PROFILE,

        NEWS_LISTING,
        NEWS_ITEM,

        BATTLE_REPORT_LISTING,
        BATTLE_REPORT,

        FORUM_LISTING,
        FORUM_SEARCH,
        THREAD_LISTING,
        THREAD_CREATING,
        POST_LISTING,
        POST_CREATING,

        NOTIFICATION,
        PROFILE_SEARCH,
    }

    public static Fragment get(final Type type) {
        return get(type, null);
    }

    public static Fragment get(final Type type, final Bundle data) {
        switch (type) {
            case ACCOUNT_PROFILE:
                return AccountProfileFragment.newInstance(data);
            case BATTLE_FEED:
                return BattleFeedFragment.newInstance(data);
            case PLATOON_PROFILE:
                return PlatoonProfileFragment.newInstance(data);
            case SOLDIER_OVERVIEW:
                return SoldierOverviewFragment.newInstance(data);
            case SOLDIER_STATS:
                return SoldierStatsFragment.newInstance(data);
            case WEAPON_STATS:
                return WeaponStatsFragment.newInstance(data);
            case VEHICLE_STATS:
                return VehicleStatsFragment.newInstance(data);
            case NEWS_LISTING:
                return NewsListingFragment.newInstance(data);
            case NEWS_ITEM:
                return NewsArticleFragment.newInstance(data);
            case BATTLE_FEED_POSTING:
                return BattleFeedPostingFragment.newInstance(data);
            case FORUM_LISTING:
                return ForumListingFragment.newInstance(data);
            case THREAD_LISTING:
                return ThreadListingFragment.newInstance(data);
            case THREAD_CREATING:
                return ThreadCreationFragment.newInstance(data);
            case POST_LISTING:
                return PostListingFragment.newInstance(data);
            case POST_CREATING:
                return PostCreationFragment.newInstance(data);
            case NOTIFICATION:
                return NotificationFragment.newInstance(data);
            case BATTLE_REPORT:
                return BattleReportFragment.newInstance(data);
            case BATTLE_REPORT_LISTING:
                return BattleReportListingFragment.newInstance(data);
            case PROFILE_SEARCH:
                return ProfileSearchFragment.newInstance(data);
            default:
                throw new TypeNotPresentException("" + type, null);
        }
    }

    public static Fragment fromOrdinal(final int ordinal) {
        return fromOrdinal(ordinal, null);
    }

    public static Fragment fromOrdinal(final int ordinal, final Bundle data) {
        for (Type type : Type.values()) {
            if (type.ordinal() == ordinal) {
                return get(type, data);
            }
        }
        throw new IllegalArgumentException("Invalid ordinal: " + ordinal);

    }
}
