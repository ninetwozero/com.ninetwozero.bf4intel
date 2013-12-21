package com.ninetwozero.bf4intel.ui.unlocks;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.ui.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class UnlockActivity extends BaseIntelActivity implements ActionBar.TabListener {
    public static final String INTENT_PROFILE = "profile";

    private static final int ID_LOADER = 124343;
    private static final int[] TITLES = new int[] {
        R.string.weapons,
        R.string.vehicles,
        R.string.kits
    };

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_multi_tab_activity);
        initialize();
    }

    @Override
    public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {}

    private void initialize() {
        setupViewPagerAdapter();
        setupViewPager();
        setupActionBar();
    }

    private void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), generateFragmentList());
    }

    private List<Fragment> generateFragmentList() {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        final Bundle profileBundle = getIntent().getBundleExtra(INTENT_PROFILE);

        fragments.add(FragmentFactory.get(FragmentFactory.Type.WEAPON_UNLOCKS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.VEHICLE_UNLOCKS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.KIT_UNLOCKS, profileBundle));

        return fragments;
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(viewPagerAdapter.getCount());
        viewPager.setOnPageChangeListener(
            new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    getActionBar().setSelectedNavigationItem(position);
                }
            }
        );
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void setupActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0, max = TITLES.length; i < max; i++) {
            actionBar.addTab(actionBar.newTab().setText(TITLES[i]).setTabListener(this));
        }
    }
}
