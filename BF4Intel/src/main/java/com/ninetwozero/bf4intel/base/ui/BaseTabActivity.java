package com.ninetwozero.bf4intel.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.ui.adapters.ViewPagerAdapter;
import com.ninetwozero.bf4intel.utils.GoogleAnalytics;

import java.util.List;

public abstract class BaseTabActivity extends BaseIntelActivity implements ActionBar.TabListener {
    public static final String INTENT_EXTRA = "extra";

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_multi_tab_activity);
        initialize();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    private void initialize() {
        setupViewPagerAdapter();
        setupViewPager();
        setupActionBar();

        //This is to record initial opening of viewPager
        postToGoogleAnalytics(0);
    }

    private void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), generateFragmentList());
    }

    private List<Fragment> generateFragmentList() {
        final Bundle dataBundle = getBundleFromIntent(getIntent());
        dataBundle.putBoolean(BaseFragment.FLAG_DISABLE_AUTOMATIC_ANALYTICS, true);
        dataBundle.putBoolean(BaseFragment.FLAG_DISABLE_RETAIN_STATE, true);
        return fetchFragmentsForActivity(dataBundle);
    }

    private Bundle getBundleFromIntent(final Intent intent) {
        return intent.hasExtra(INTENT_EXTRA) ? intent.getBundleExtra(INTENT_EXTRA) : new Bundle();
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(getOffscreenPageLimit());
        viewPager.setOnPageChangeListener(
            new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    getActionBar().setSelectedNavigationItem(position);
                    postToGoogleAnalytics(position);
                }
            }
        );
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void postToGoogleAnalytics(int position) {
        String fragmentName = viewPagerAdapter.getItem(position).getClass().getSimpleName();
        GoogleAnalytics.post(this, fragmentName);
    }

    private void setupActionBar() {
        final int[] titles = getTitleResources();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0, max = titles.length; i < max; i++) {
            actionBar.addTab(actionBar.newTab().setText(titles[i]).setTabListener(this));
        }
    }

    protected abstract int[] getTitleResources();
    protected abstract List<Fragment> fetchFragmentsForActivity(final Bundle profileBundle);
    protected abstract int getOffscreenPageLimit();
}
