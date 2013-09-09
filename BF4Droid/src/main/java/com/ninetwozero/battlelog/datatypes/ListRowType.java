package com.ninetwozero.battlelog.datatypes;

import com.ninetwozero.battlelog.R;

public enum ListRowType {
    SIDE_ACCOUNT(R.layout.side_account_box),
    SIDE_SOLDIER(R.layout.side_soldier_box),
    SIDE_PLATOON(R.layout.side_platoon_box),
    SIDE_REGULAR(R.layout.side_regular),
    SIDE_REGULAR_OPTION(R.layout.side_regular_option),
    SIDE_HEADING(R.layout.side_heading),
    SIDE_FEED(R.layout.side_regular),


    PROFILE_ACCOUNT(R.layout.list_profile_account),
    PROFILE_SOLDIER(R.layout.list_profile_soldier),
    PROFILE_PLATOON(R.layout.list_profile_platoon),

    HEADING(R.layout.generic_heading);

    private int mId;
    ListRowType(final int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public boolean isEnabled() {
        switch( this ) {
            case SIDE_HEADING:
            case HEADING:
            case PROFILE_ACCOUNT:
                return false;
            default:
                return true;
        }
    }
}