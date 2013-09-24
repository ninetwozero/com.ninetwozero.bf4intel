package com.ninetwozero.battlelog.datatypes;

import com.ninetwozero.battlelog.R;

public enum ListRowType {
    SIDE_ACCOUNT,
    SIDE_SOLDIER,
    SIDE_PLATOON,
    SIDE_REGULAR,
    SIDE_REGULAR_CHILD,
    SIDE_HEADING,
    SIDE_FEED,

    PROFILE_ACCOUNT,
    PROFILE_SOLDIER,
    PROFILE_PLATOON,

    HEADING;

    public static final int SIZE = ListRowType.values().length;

    public boolean isEnabled() {
        switch (this) {
            case SIDE_HEADING:
            case HEADING:
            case PROFILE_ACCOUNT:
                return false;
            default:
                return true;
        }
    }

    public static int getResource(final ListRowType type) {
        switch (type) {
            case SIDE_ACCOUNT:
                return R.layout.side_account_box;
            case SIDE_SOLDIER:
                return R.layout.side_soldier_box;
            case SIDE_PLATOON:
                return R.layout.side_platoon_box;
            case SIDE_REGULAR_CHILD:
                return R.layout.side_regular_child;
            case SIDE_HEADING:
                return R.layout.side_heading;
            case SIDE_FEED:
            case SIDE_REGULAR:
                return R.layout.side_regular;
            case PROFILE_ACCOUNT:
                return R.layout.list_item_profile_account;
            case PROFILE_SOLDIER:
                return R.layout.list_item_profile_soldier;
            case PROFILE_PLATOON:
                return R.layout.list_item_profile_platoon;
            case HEADING:
                return R.layout.generic_heading;

            default:
                return R.layout.side_regular;
        }
    }
}