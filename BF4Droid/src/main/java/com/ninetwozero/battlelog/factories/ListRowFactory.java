package com.ninetwozero.battlelog.factories;

import android.content.Intent;
import android.os.Bundle;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;

public class ListRowFactory {
    private ListRowFactory() {}

    public static ListRow create(final ListRowType type, final Bundle data) {
        switch( type ) {
            case SIDE_ACCOUNT:
                return getItemForAccountTypeInMenu(type, data);
            case PROFILE_ACCOUNT:
                return getItemForAccountTypeInProfile(type, data);
            case SIDE_SOLDIER:
            case PROFILE_SOLDIER:
                return getItemForSoldierType(type, data);
            case SIDE_PLATOON:
            case PROFILE_PLATOON:
                return getItemForPlatoonType(type, data);
            case SIDE_FEED:
                return getItemForFeedType(type, data);
            default:
                throw new UnsupportedOperationException("Invalid call for type: " + type);
        }
    }

    public static ListRow create(final ListRowType type, final String text) {
        return new ListRow(type, text);
    }

    public static ListRow create(final ListRowType type, final String text, final Intent intent) {
        return new ListRow(type, text, intent);
    }

    public static ListRow create(final ListRowType type, final String text, final FragmentFactory.Type fragmentType) {
        return new ListRow(type, text, fragmentType);
    }

    private static ListRow getItemForAccountTypeInMenu(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.user_account), "NINETWOZERO");
        stringMappings.putString(String.valueOf(R.id.user_email), "kalle@n20.se");
        drawableMappings.putInt(String.valueOf(R.id.gravatar), R.drawable.test_gravatar);

        return new ListRow(
            type,
            FragmentFactory.Type.ACCOUNT_PROFILE,
            stringMappings,
            drawableMappings
        );
    }

    private static ListRow getItemForAccountTypeInProfile(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.user_name), "Karl Lindmark");
        stringMappings.putString(String.valueOf(R.id.user_age), "22");
        stringMappings.putString(String.valueOf(R.id.user_enlistdate), "2013-09-03");
        stringMappings.putString(String.valueOf(R.id.user_presentation), "Hello world!");
        stringMappings.putString(String.valueOf(R.id.user_country), "SWEDEN");

        return new ListRow(
            type,
            null,
            stringMappings,
            drawableMappings
        );
    }

    private static ListRow getItemForSoldierType(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.soldier_name), "NINETWOZERO");
        drawableMappings.putInt(String.valueOf(R.id.soldier_image), R.drawable.test_soldier);
        drawableMappings.putInt(String.valueOf(R.id.soldier_platform), R.drawable.test_platform);
        drawableMappings.putInt(String.valueOf(R.id.soldier_rank), R.drawable.test_rank31);

        return new ListRow(
            type,
            FragmentFactory.Type.SOLDIER_STATS, //TODO: Create fragment that opens *displayed* soldier?
            stringMappings,
            drawableMappings
        );
    }

    private static ListRow getItemForPlatoonType(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.platoon_name), "Chili-powered Zebras");
        drawableMappings.putInt(String.valueOf(R.id.platoon_image), R.drawable.test_platoon);
        drawableMappings.putInt(String.valueOf(R.id.platoon_platform), R.drawable.test_platform);

        return new ListRow(
            type,
            FragmentFactory.Type.PLATOON_PROFILE,
            stringMappings,
            drawableMappings
        );
    }

    private static ListRow getItemForFeedType(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.text1), "BATTLE FEED");

        return new ListRow(
            type,
            FragmentFactory.Type.BATTLE_FEED,
            stringMappings,
            drawableMappings
        );
    }
}
