package com.ninetwozero.bf4intel.ui.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseTabActivity;

import java.util.ArrayList;
import java.util.List;

public class AppInfoActivity extends BaseTabActivity {
    private static final int[] TITLES = {
        R.string.label_about,
        R.string.label_updates,
        R.string.label_credits,
        R.string.label_opensource,
    };

    @Override
    protected int[] getTitleResources() {
        return TITLES;
    }

    @Override
    protected List<Fragment> fetchFragmentsForActivity(final Bundle data) {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(AboutFragment.newInstance(data));
        fragments.add(ChangelogFragment.newInstance(data));
        fragments.add(CreditListFragment.newInstance(data));
        fragments.add(OpenSourceInfoFragment.newInstance(data));
        return fragments;
    }

    @Override
    protected int getOffscreenPageLimit() {
        return 0;
    }
}
