package com.ninetwozero.bf4intel.ui.unlocks;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.ui.adapters.ViewPagerAdapter;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.utils.Result;

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
    protected void onResume() {
        super.onResume();
        //getSupportLoaderManager().initLoader(ID_LOADER, null, this);
    }

    /*
        TODO: Get personas for profile X via loader

        - Get personas
        - Populate ACtionBar dropdown
        - onChange @ ActionBar dropdown, post persona data for fragments (for update+reload)
    */

    @Override
    public Loader<Result> onCreateLoader(int i, Bundle bundle) {
        //return new IntelLoader(getApplicationContext(), new SimpleGetRequest(UrlFactory.buildAwardsURL(200661244, 1)));
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Result> resultLoader, Result result) {
        if (result == Result.SUCCESS) {
            processResult(result.getResultMessage());
        } else {
            processError(result.getResultMessage());
        }
    }

    @Override
    public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction fragmentTransaction) {}

    /* TODO:
        Create interface matching current BaseLoadingListFragment abstracts
     */
    private void processResult(String resultMessage) {
        JsonObject dataJson = extractFromJson(resultMessage);
        Awards awards = gson.fromJson(dataJson, Awards.class);
        showLoadingStateInActionBar(false);
        BusProvider.getInstance().post(awards);
    }

    private void processError(String resultMessage) {
        Log.e(UnlockActivity.class.getSimpleName(), resultMessage);
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
        final List<Fragment> fragments = new ArrayList<Fragment>();
        final Bundle profileBundle = getIntent().getBundleExtra(INTENT_PROFILE);

        fragments.add(FragmentFactory.get(FragmentFactory.Type.WEAPON_UNLOCKS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.VEHICLE_UNLOCKS, profileBundle));
        fragments.add(FragmentFactory.get(FragmentFactory.Type.KIT_UNLOCKS, profileBundle));

        return fragments;
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
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
