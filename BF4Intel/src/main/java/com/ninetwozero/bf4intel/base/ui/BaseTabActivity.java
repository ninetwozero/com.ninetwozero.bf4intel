package com.ninetwozero.bf4intel.base.ui;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.adapters.ViewPagerAdapter;

import java.util.List;

public abstract class BaseTabActivity extends BaseIntelActivity implements ActionBar.TabListener {
    public static final String INTENT_EXTRA = "profile";
    private static final int PAGE_LIMIT = 1;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_multi_tab_activity);
        initialize();
        //This is to record initial opening of viewPager
        postToGoogleAnalytics(0);
    }

    @Override
    public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
    }

    private void initialize() {
        setupViewPagerAdapter();
        setupViewPager();
        setupActionBar();
    }

    private void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), generateFragmentList());
    }

    private List<Fragment> generateFragmentList() {
        final Bundle dataBundle = getIntent().getBundleExtra(INTENT_EXTRA);
        return fetchFragmentsForActivity(dataBundle);
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(PAGE_LIMIT);
        viewPager.setOnPageChangeListener(
            new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    getActionBar().setSelectedNavigationItem(position);
                }
            }
        );
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                postToGoogleAnalytics(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void postToGoogleAnalytics(int position) {
        String fragmentName = viewPagerAdapter.getItem(position).getClass().getSimpleName();
        googleAnalytics(fragmentName);
    }

    private void setupActionBar() {
        final int[] titles = getTitleResources();
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0, max = titles.length; i < max; i++) {
            actionBar.addTab(actionBar.newTab().setText(titles[i]).setTabListener(this));
        }
    }

    protected void googleAnalytics(String fragmentName) {
        EasyTracker tracker = EasyTracker.getInstance(this);
        tracker.set(Fields.SCREEN_NAME, fragmentName);
        Log.e("Analytics", fragmentName);
        tracker.send(MapBuilder.createAppView().build());
    }

    protected abstract int[] getTitleResources();
    protected abstract List<Fragment> fetchFragmentsForActivity(final Bundle profileBundle);
}
