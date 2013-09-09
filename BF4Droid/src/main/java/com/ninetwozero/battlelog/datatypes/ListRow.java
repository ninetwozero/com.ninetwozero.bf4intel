package com.ninetwozero.battlelog.datatypes;

import android.content.Intent;
import android.os.Bundle;

import com.ninetwozero.battlelog.factories.FragmentFactory;

public class ListRow {
    private String mTitle;
    private ListRowType mType;

    private Intent mIntent;
    private FragmentFactory.Type mFragmentType;

    private Bundle mStringMappings;
    private Bundle mDrawableMappings;

    public ListRow(final ListRowType type, final String title) {
        mType = type;
        mTitle = title;

        mStringMappings = new Bundle();
        mDrawableMappings = new Bundle();
    }

    public ListRow(final ListRowType type, final String title, final Intent intent) {
        mType = type;
        mTitle = title;
        mIntent = intent;

        mStringMappings = new Bundle();
        mDrawableMappings = new Bundle();
    }

    public ListRow(final ListRowType type, final FragmentFactory.Type fragmentType, final Bundle strings, final Bundle drawables) {
        mType = type;
        mFragmentType = fragmentType;
        mTitle = "N/A";

        mStringMappings = strings;
        mDrawableMappings = drawables;
    }

    public String getTitle() {
        return mTitle;
    }

    public ListRowType getType() {
        return mType;
    }

    public int getLayout() {
        return ListRowType.getResource(mType);
    }

    public Intent getIntent() {
        return mIntent;
    }

    public FragmentFactory.Type getFragmentType() {
        return mFragmentType;
    }

    public Bundle getStringMappings() {
        return mStringMappings;
    }

    public Bundle getDrawableMappings() {
        return mDrawableMappings;
    }

    public boolean hasIntent() {
        return mIntent != null;
    }

    public boolean hasFragmentType() {
        return mFragmentType != null;
    }
}
