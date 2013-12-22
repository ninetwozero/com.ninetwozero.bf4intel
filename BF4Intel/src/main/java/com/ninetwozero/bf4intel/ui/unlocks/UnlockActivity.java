package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseTabActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

public class UnlockActivity extends BaseTabActivity {
    private static final int[] TITLES = new int[]{
        R.string.weapons,
        R.string.vehicles,
        R.string.kits
    };

    @Override
    protected int[] getTitleResources() {
        return TITLES;
    }

    @Override
    protected List<Fragment> fetchFragmentsForActivity(final Bundle profileBundle) {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentFactory.get(FragmentFactory.Type.WEAPON_UNLOCKS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.VEHICLE_UNLOCKS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.KIT_UNLOCKS, profileBundle));
        return fragments;
    }
}
