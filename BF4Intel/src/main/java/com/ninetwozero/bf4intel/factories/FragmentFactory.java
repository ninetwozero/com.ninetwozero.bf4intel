package com.ninetwozero.bf4intel.factories;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.ninetwozero.bf4intel.fragments.*;

public class FragmentFactory {
    public enum Type {
        ACCOUNT_PROFILE,

        SOLDIER_OVERVIEW,
        SOLDIER_STATS,
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
        THREAD_LISTING,
        THREAD_CREATING,
        POST_LISTING,
        POST_CREATING,

        NOTIFICATION,
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
            //case SOLDIER_UNLOCKS:
            //    return SoldierOverviewFragment.newInstance(data);
            /*case SOLDIER_ASSIGNMENTS:
                return AssignmentsFragment.newInstance(data);*/
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
            case BATTLE_REPORT_LISTING:
                return BattleReportListingFragment.newInstance(data);
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
