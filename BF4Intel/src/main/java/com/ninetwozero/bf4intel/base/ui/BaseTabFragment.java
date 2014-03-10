package com.ninetwozero.bf4intel.base.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.adapters.ViewPagerAdapter;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;

import java.util.List;

public abstract class BaseTabFragment extends BaseFragment {
    private ViewPagerAdapter viewPagerAdapter;
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

        //This is to record initial opening of viewPager
        postToGoogleAnalytics(0);
    }

    private void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(
            getFragmentManager(),
            getTitleResources(),
            generateFragmentList(getPreparedArguments())
        );
    }

    private Bundle getPreparedArguments() {
        final Bundle arguments = getArguments();
        arguments.putBoolean(BaseFragment.CALLED_FROM_VIEWPAGER, true);
        return arguments;
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
        tabStrip.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                postToGoogleAnalytics(position);
            }
        });
    }

    private void postToGoogleAnalytics(int position) {
        String fragmentName = viewPagerAdapter.getItem(position).getClass().getSimpleName();
        GoogleAnalytics.post(getActivity(), fragmentName);
    }

    protected abstract List<Fragment> generateFragmentList(final Bundle data);
    protected abstract List<String> getTitleResources();
    protected abstract int getOffscreenPageLimit();
}
