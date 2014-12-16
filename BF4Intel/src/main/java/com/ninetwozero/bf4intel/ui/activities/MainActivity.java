package com.ninetwozero.bf4intel.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.events.TrackingNewSoldierEvent;
import com.ninetwozero.bf4intel.ui.fragments.NavigationDrawerFragment;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;

public class MainActivity extends BaseIntelActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_DRAWER_OPENED = "isDrawerOpened";

    private boolean userLearnedDrawer;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private View fragmentContainerView;
    private NavigationDrawerFragment navigationDrawer;
    private int title = R.string.empty;

    private boolean shouldShowDualPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleNavigationDrawer(!isDrawerOpen());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (!shouldShowDualPane && isDrawerOpen()) {
            toggleNavigationDrawer(false);
        }

        if (navigationDrawer.fetchDefaultPosition() != navigationDrawer.getCheckedItemPosition()) {
            navigationDrawer.checkDefaultItemPosition();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (shouldShowDualPane) {
            outState.putBoolean(STATE_DRAWER_OPENED, false);
        } else {
            outState.putBoolean(STATE_DRAWER_OPENED, isDrawerOpen());
        }
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
    public void closeDrawer() {
        if (drawerLayout == null) {
            return;
        }
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public void onNavigationDrawerItemSelected(final int position, final boolean shouldClose, final int titleResource) {
        final boolean hasNewTitle = titleResource > 0;
        this.title = hasNewTitle ? titleResource : this.title;

        if (drawerLayout != null && shouldClose) {
            toggleNavigationDrawer(false);
        }

        if (shouldShowDualPane || hasNewTitle) {
            toolbar.setTitle(this.title);
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LoginActivity.REQUEST_PROFILE && resultCode == Activity.RESULT_OK) {
            final Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }

            final TrackingNewSoldierEvent event = new TrackingNewSoldierEvent(data.getExtras());
            navigationDrawer.onStartedTrackingNewProfile(event);
            BusProvider.getInstance().post(event);
        }
    }

    private void initialize(final Bundle savedInstanceState) {
        shouldShowDualPane = getResources().getBoolean(R.bool.main_is_dualpane);

        navigationDrawer = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentByTag("NavigationDrawerFragment");
        setupActionBar();
        if (!shouldShowDualPane) {
            setupActionBarToggle();
        }
        setupActivityFromState(savedInstanceState);
    }

    private void setupActionBarToggle() {
        userLearnedDrawer = sharedPreferences.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        fragmentContainerView = findViewById(R.id.navigation_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerToggle = new ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                if (!navigationDrawer.isAdded()) {
                    return;
                }
                toolbar.setTitle(title);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                if (!navigationDrawer.isAdded()) {
                    return;
                }

                if (!userLearnedDrawer) {
                    userLearnedDrawer = true;
                    sharedPreferences.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    private void setupActivityFromState(final Bundle state) {
        if (state != null && !shouldShowDualPane) {
            toggleNavigationDrawer(state.getBoolean(STATE_DRAWER_OPENED, false));
        }
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
