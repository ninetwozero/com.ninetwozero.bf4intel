package com.ninetwozero.bf4intel.factories;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.ui.assignments.AssignmentDetailFragment;
import com.ninetwozero.bf4intel.ui.assignments.AssignmentGridFragment;
import com.ninetwozero.bf4intel.ui.awards.AwardDetailFragment;
import com.ninetwozero.bf4intel.ui.awards.AwardGridFragment;
import com.ninetwozero.bf4intel.ui.battlefeed.BattleFeedFragment;
import com.ninetwozero.bf4intel.ui.battlefeed.BattleFeedPostingFragment;
import com.ninetwozero.bf4intel.ui.fragments.AccountProfileFragment;
import com.ninetwozero.bf4intel.ui.fragments.ForumListingFragment;
import com.ninetwozero.bf4intel.ui.fragments.HomeFragment;
import com.ninetwozero.bf4intel.ui.fragments.NotificationFragment;
import com.ninetwozero.bf4intel.ui.fragments.PostCreationFragment;
import com.ninetwozero.bf4intel.ui.fragments.PostListingFragment;
import com.ninetwozero.bf4intel.ui.fragments.SoldierOverviewFragment;
import com.ninetwozero.bf4intel.ui.fragments.ThreadCreationFragment;
import com.ninetwozero.bf4intel.ui.fragments.ThreadListingFragment;
import com.ninetwozero.bf4intel.ui.news.NewsArticleFragment;
import com.ninetwozero.bf4intel.ui.news.NewsListingFragment;
import com.ninetwozero.bf4intel.ui.search.ProfileSearchFragment;
import com.ninetwozero.bf4intel.ui.stats.SoldierStatisticsTabFragment;
import com.ninetwozero.bf4intel.ui.stats.details.DetailedStatsFragment;
import com.ninetwozero.bf4intel.ui.stats.reports.BattleReportFragment;
import com.ninetwozero.bf4intel.ui.stats.reports.BattleReportListingFragment;
import com.ninetwozero.bf4intel.ui.stats.vehicles.VehicleStatsFragment;
import com.ninetwozero.bf4intel.ui.stats.weapons.WeaponDetailsFragment;
import com.ninetwozero.bf4intel.ui.stats.weapons.WeaponStatsFragment;
import com.ninetwozero.bf4intel.ui.unlocks.UnlockTabFragment;
import com.ninetwozero.bf4intel.ui.unlocks.kits.KitUnlockFragment;
import com.ninetwozero.bf4intel.ui.unlocks.vehicles.VehicleUnlockFragment;
import com.ninetwozero.bf4intel.ui.unlocks.weapons.WeaponUnlockFragment;

public class FragmentFactory {
    public static Fragment get(final Type type) {
        return get(type, null);
    }

    public static Fragment get(final Type type, final Bundle data) {
        switch (type) {
            case HOME:
                return HomeFragment.newInstance(data);
            case ACCOUNT_PROFILE:
                return AccountProfileFragment.newInstance(data);
            case BATTLE_FEED:
                return BattleFeedFragment.newInstance(data);
            case SOLDIER_OVERVIEW:
                return SoldierOverviewFragment.newInstance(data);
            case SOLDIER_STATS:
                return SoldierStatisticsTabFragment.newInstance(data);
            case SOLDIER_ASSIGNMENTS:
                return AssignmentGridFragment.newInstance(data);
            case SOLDIER_ASSIGNMENT_DETAILS:
                return AssignmentDetailFragment.newInstance(data);
            case SOLDIER_AWARDS:
                return AwardGridFragment.newInstance(data);
            case SOLDIER_AWARD_DETAILS:
                return AwardDetailFragment.newInstance(data);
            case WEAPON_STATS:
                return WeaponStatsFragment.newInstance(data);
            case WEAPON_STATS_DETAILS:
                return WeaponDetailsFragment.newInstance(data);
            case VEHICLE_STATS:
                return VehicleStatsFragment.newInstance(data);
            case DETAILS_STATS:
                return DetailedStatsFragment.newInstance(data);
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
            case SOLDIER_UNLOCKS:
                return UnlockTabFragment.newInstance(data);
            case WEAPON_UNLOCKS:
                return WeaponUnlockFragment.newInstance(data);
            case VEHICLE_UNLOCKS:
                return VehicleUnlockFragment.newInstance(data);
            case KIT_UNLOCKS:
                return KitUnlockFragment.newInstance(data);
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

    public enum Type {
        HOME,

        ACCOUNT_PROFILE,

        SOLDIER_OVERVIEW,
        SOLDIER_STATS,
        WEAPON_STATS,
        WEAPON_STATS_DETAILS,
        VEHICLE_STATS,
        DETAILS_STATS,

        SOLDIER_UNLOCKS,
        WEAPON_UNLOCKS,
        VEHICLE_UNLOCKS,
        KIT_UNLOCKS,

        SOLDIER_ASSIGNMENTS,
        SOLDIER_ASSIGNMENT_DETAILS,

        SOLDIER_AWARDS,
        SOLDIER_AWARD_DETAILS,

        BATTLE_FEED,
        BATTLE_FEED_POSTING,

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
}
