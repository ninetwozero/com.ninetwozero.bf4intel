package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseTabFragment;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class UnlockTabFragment extends BaseTabFragment {
    private final int[] TITLES = { R.string.weapons, R.string.vehicles, R.string.kits };

    public UnlockTabFragment() {
    }

    public static UnlockTabFragment newInstance(final Bundle data) {
        final UnlockTabFragment fragment = new UnlockTabFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected List<Fragment> generateFragmentList(final Bundle data) {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentFactory.get(FragmentFactory.Type.WEAPON_UNLOCKS, data));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.VEHICLE_UNLOCKS, data));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.KIT_UNLOCKS, data));
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
