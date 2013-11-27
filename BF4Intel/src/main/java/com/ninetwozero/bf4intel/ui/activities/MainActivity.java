package com.ninetwozero.bf4intel.ui.activities;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.ui.fragments.NavigationDrawerFragment;

public class MainActivity extends BaseIntelActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_DRAWER_OPENED = "isDrawerOpened";

    private boolean isRecreated = false;
    private boolean userLearnedDrawer;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private View fragmentContainerView;
    private NavigationDrawerFragment navigationDrawer;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationDrawer();
        setupActionBar();
        setupActionBarToggle();
        setupActivityFromState(savedInstanceState);
    }

    private void setupNavigationDrawer() {
        navigationDrawer = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    private void setupActionBarToggle() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        fragmentContainerView = findViewById(R.id.navigation_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        drawerToggle = new ActionBarDrawerToggle(
                this,
            drawerLayout,
                R.drawable.ic_navigation_drawer,
                R.string.app_name,
                R.string.app_name
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                if (!navigationDrawer.isAdded()) {
                    return;
                }
                getActionBar().setTitle(title);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (!navigationDrawer.isAdded()) {
                    return;
                }

                if (!userLearnedDrawer) {
                    userLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }
            }
        };

        if (!userLearnedDrawer) {
            drawerLayout.openDrawer(fragmentContainerView);
        }

        drawerLayout.post(
            new Runnable() {
                @Override
                public void run() {
                    drawerToggle.syncState();
                }
            }
        );
        drawerLayout.setDrawerListener(drawerToggle);
    }


    private void setupActionBar() {
        final ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("");
    }

    private void setupActivityFromState(final Bundle state) {
        if (state != null) {
            toggleNavigationDrawer(state.getBoolean(STATE_DRAWER_OPENED, false));
            isRecreated = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);

        final MenuItem menuItem = menu.findItem(R.id.ab_action_refresh);
        if ( menuItem != null ) {
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleNavigationDrawer(!isDrawerOpen());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            toggleNavigationDrawer(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_DRAWER_OPENED, isDrawerOpen());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean isDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(fragmentContainerView);
    }


    @Override
    public void onNavigationDrawerItemSelected(final int position, final String title) {
        this.title = title == null? this.title : title.toUpperCase();
        if (drawerLayout != null && !isRecreated) {
            toggleNavigationDrawer(false);
        }
        isRecreated = false;
    }

    private void toggleNavigationDrawer(final boolean show) {
        if (show) {
            drawerLayout.openDrawer(fragmentContainerView);
            navigationDrawer.setMenuVisibility(true);
        } else {
            drawerLayout.closeDrawer(fragmentContainerView);
            navigationDrawer.setMenuVisibility(false);
        }
    }
}
