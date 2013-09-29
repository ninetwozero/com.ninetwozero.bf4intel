package com.ninetwozero.battlelog.factories;

import android.app.Fragment;
import android.os.Bundle;

import com.ninetwozero.battlelog.fragments.*;

public class FragmentFactory {
    public enum Type {
        ACCOUNT_PROFILE,
        SOLDIER_STATS,

        BATTLE_FEED,
        BATTLE_FEED_POSTING,

        PLATOON_PROFILE,

        NEWS_LISTING,
        NEWS_ITEM,

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
            case BATTLE_FEED_POSTING:
                return BattleFeedPostingFragment.newInstance();
            case FORUM_LISTING:
                return ForumListingFragment.newInstance();
            case THREAD_LISTING:
                return ThreadListingFragment.newInstance();
            case THREAD_CREATING:
                return ThreadCreationFragment.newInstance();
            case POST_LISTING:
                return PostListingFragment.newInstance();
            case POST_CREATING:
                return PostCreationFragment.newInstance();
            case NOTIFICATION:
                return NotificationFragment.newInstance();
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
