package com.ninetwozero.bf4intel.ui.stats;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseTabActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class SoldierStatisticsActivity extends BaseTabActivity {
    private static final int[] TITLES = new int[]{
        R.string.weapons,
        R.string.vehicles,
        R.string.reports
    };

    @Override
    protected int[] getTitleResources() {
        return TITLES;
    }

    @Override
    protected List<Fragment> fetchFragmentsForActivity(final Bundle profileBundle) {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentFactory.get(FragmentFactory.Type.WEAPON_STATS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.VEHICLE_STATS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.BATTLE_REPORT_LISTING, profileBundle));
        return fragments;
    }
}
