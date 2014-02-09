package com.ninetwozero.bf4intel.factories;

import android.content.Intent;
import android.os.Bundle;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.interfaces.ListRowElement;
import com.ninetwozero.bf4intel.json.login.SummarizedSoldierStats;
import com.ninetwozero.bf4intel.menu.ListRowType;
import com.ninetwozero.bf4intel.menu.NormalRow;
import com.ninetwozero.bf4intel.menu.SoldierSpinnerRow;

import java.util.List;

public class ListRowFactory {
    private ListRowFactory() {
    }

    public static NormalRow create(final ListRowType type, final String text) {
        return new NormalRow.Builder(type).title(text).build();
    }

    public static ListRowElement create(final ListRowType type, final Bundle data) {
        switch (type) {
            case SIDE_ACCOUNT:
                return getItemForAccountTypeInMenu(type, data);
            case PROFILE_ACCOUNT:
                return getItemForAccountTypeInProfile(type, data);
            default:
                throw new UnsupportedOperationException("Invalid call for type: " + type);
        }
    }

    public static NormalRow create(final ListRowType type, final String text, final Bundle data) {
        return new NormalRow.Builder(type).title(text).data(data).build();
    }

    public static NormalRow create(final ListRowType type, final String text, final Bundle data, final Intent intent) {
        return new NormalRow.Builder(type).title(text).data(data).intent(intent).build();
    }

    public static NormalRow create(final ListRowType type, final String text, final Bundle data, final FragmentFactory.Type fragmentType) {
        return new NormalRow.Builder(type).title(text).data(data).fragmentType(fragmentType).build();
    }

    private static NormalRow getItemForAccountTypeInMenu(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.user_account), "NINETWOZERO");
        stringMappings.putString(String.valueOf(R.id.user_email), "kalle@n20.se");
        drawableMappings.putInt(String.valueOf(R.id.gravatar), R.drawable.test_gravatar);

        return new NormalRow.Builder(type)
                .fragmentType(FragmentFactory.Type.ACCOUNT_PROFILE)
                .stringMappings(stringMappings)
                .drawableMappings(drawableMappings)
                .data(data)
                .build();
    }

    private static NormalRow getItemForAccountTypeInProfile(final ListRowType type, final Bundle data) {
        final Bundle stringMappings = new Bundle();
        final Bundle drawableMappings = new Bundle();

        stringMappings.putString(String.valueOf(R.id.user_name), "Karl Lindmark");
        stringMappings.putString(String.valueOf(R.id.user_age), "22");
        stringMappings.putString(String.valueOf(R.id.user_enlistdate), "2013-09-03");
        stringMappings.putString(String.valueOf(R.id.user_presentation), "Hello world!");
        stringMappings.putString(String.valueOf(R.id.user_country), "SWEDEN");

        return new NormalRow.Builder(type)
                .stringMappings(stringMappings)
                .drawableMappings(drawableMappings)
                .data(data)
                .build();
    }

    public static SoldierSpinnerRow createSoldierRow(final List<SummarizedSoldierStats> soldiers) {
       return new SoldierSpinnerRow(soldiers);
    }
}
