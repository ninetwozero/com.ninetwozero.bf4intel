package com.ninetwozero.bf4intel.ui.stats;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseTabFragment;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class SoldierStatisticsTabFragment extends BaseTabFragment {
    private final int[] TITLES = { R.string.weapons, R.string.vehicles, R.string.reports, R.string.details };

    public SoldierStatisticsTabFragment() {
    }

    public static SoldierStatisticsTabFragment newInstance(final Bundle data) {
        final SoldierStatisticsTabFragment fragment = new SoldierStatisticsTabFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected List<Fragment> generateFragmentList(final Bundle data) {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentFactory.get(FragmentFactory.Type.WEAPON_STATS, data));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.VEHICLE_STATS, data));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.BATTLE_REPORT_LISTING, data));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.DETAILS_STATS, data));
        return fragments;
    }

    @Override
    protected List<String> getTitleResources() {
        final List<String> titles = new ArrayList<String>();
        for (int title : TITLES) {
            titles.add(getString(title));
        }
        return titles;
    }


    @Override
    protected int getOffscreenPageLimit() {
        return 1;
    }
}
