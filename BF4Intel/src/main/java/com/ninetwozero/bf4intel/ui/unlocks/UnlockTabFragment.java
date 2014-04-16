package com.ninetwozero.bf4intel.ui.unlocks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseTabFragment;
import com.ninetwozero.bf4intel.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.util.ArrayList;
import java.util.List;

public class UnlockTabFragment extends BaseTabFragment {
    public static final String KEY_SORT_MODE = "unlocksSortMode";
    private final int[] TITLES = { R.string.weapons, R.string.vehicles, R.string.kits };

    public UnlockTabFragment() {
    }

    public static UnlockTabFragment newInstance(final Bundle data) {
        final UnlockTabFragment fragment = new UnlockTabFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_unlocks, menu);

        setupSortModeMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.ab_action_sort_categorized:
                handleSortingRequest(SortMode.CATEGORIZED);
                return true;
            case R.id.ab_action_sort_progress:
                handleSortingRequest(SortMode.PROGRESS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleSortingRequest(SortMode sortMode) {
        sharedPreferences.edit().putInt(KEY_SORT_MODE, sortMode.ordinal()).commit();
        BusProvider.getInstance().post(new RefreshEvent());
    }

    private void setupSortModeMenu(Menu menu) {
        final SortMode mode = SortMode.fromOrdinal(sharedPreferences.getInt(KEY_SORT_MODE, 0));
        switch (mode) {
            case CATEGORIZED:
                menu.findItem(R.id.ab_action_sort_categorized).setChecked(true);
                break;
            case PROGRESS:
                menu.findItem(R.id.ab_action_sort_progress).setChecked(true);
                break;
            default:
                menu.findItem(R.id.ab_action_sort_categorized).setChecked(true);
                break;
        }
    }

    @Override
    protected int getOffscreenPageLimit() {
        return 2;
    }
}
