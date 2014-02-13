package com.ninetwozero.bf4intel.base.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.adapters.ViewPagerAdapter;

import java.util.List;

public abstract class BaseTabFragment extends BaseFragment {
    private PagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = inflater.inflate(R.layout.generic_multi_tab_fragment, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupViewPagerAdapter();
        setupViewPager(view);
        setupTabs(view);
    }

    private void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(
            getFragmentManager(),
            getTitleResources(),
            generateFragmentList(getArguments())
        );
    }

    private void setupViewPager(final View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(getOffscreenPageLimit());
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void setupTabs(final View view) {
        final PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabStrip.setShouldExpand(true);
        tabStrip.setViewPager(viewPager);
    }

    protected abstract List<Fragment> generateFragmentList(final Bundle data);
    protected abstract List<String> getTitleResources();
    protected abstract int getOffscreenPageLimit();
}
