package com.ninetwozero.bf4intel.ui.activities;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.events.TrackingNewProfileEvent;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.about.AppInfoActivity;
import com.ninetwozero.bf4intel.ui.fragments.NavigationDrawerFragment;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.ninetwozero.bf4intel.ui.settings.SettingsActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;

public class MainActivity extends BaseIntelActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_DRAWER_OPENED = "isDrawerOpened";

    private boolean userLearnedDrawer;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private View fragmentContainerView;
    private NavigationDrawerFragment navigationDrawer;
    private String title;

    private boolean shouldShowDualPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        final MenuItem userSelectionItem = menu.findItem(R.id.ab_action_select_user);
        final MenuItem logoutItem = menu.findItem(R.id.ab_action_logout);
        if (userSelectionItem != null && logoutItem != null) {
            if (SessionStore.isLoggedIn()) {
                userSelectionItem.setVisible(false);
                logoutItem.setVisible(true);
            } else if (SessionStore.hasUserId()) {
                userSelectionItem.setVisible(true);
                userSelectionItem.setTitle(R.string.home_select_another_account);
                logoutItem.setVisible(false);
            } else {
                userSelectionItem.setVisible(true);
                userSelectionItem.setTitle(R.string.home_select_account);
                logoutItem.setVisible(false);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleNavigationDrawer(!isDrawerOpen());
                return true;

            case R.id.ab_action_refresh:
                BusProvider.getInstance().post(new RefreshEvent());
                return true;

            case R.id.ab_action_select_user:
                startActivityForResult(
                    new Intent(this, LoginActivity.class), LoginActivity.REQUEST_PROFILE
                );
                return true;

            case R.id.ab_action_about:
                startActivity(new Intent(this, AppInfoActivity.class));
                return true;

            case R.id.ab_action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.ab_action_logout:
                showToast("TODO: Logout functionality");
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
    public void onNavigationDrawerItemSelected(final int position, final boolean shouldClose, final String title) {
        this.title = title == null? this.title : title;
        if (drawerLayout != null && shouldClose) {
            toggleNavigationDrawer(false);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (shouldShowDualPane || actionBar.getTitle() != this.title) {
            actionBar.setTitle(this.title);
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

            final String userId = bundle.getString(Keys.Profile.ID);
            final String username = bundle.getString(Keys.Profile.USERNAME);
            final String gravatarHash = bundle.getString(Keys.Profile.GRAVATAR_HASH);
            final TrackingNewProfileEvent event = new TrackingNewProfileEvent(userId, username, gravatarHash);

            SessionStore.load(null, userId, username, gravatarHash);
            sharedPreferences
                .edit()
                .putString(Keys.Profile.ID, userId)
                .putString(Keys.Profile.USERNAME, username)
                .putString(Keys.Profile.GRAVATAR_HASH, gravatarHash)
                .commit();

            navigationDrawer.onStartedTrackingNewProfile(event);
            BusProvider.getInstance().post(event);
        }
    }

    private void initialize(final Bundle savedInstanceState) {
        shouldShowDualPane = getResources().getBoolean(R.bool.main_is_dualpane);

        navigationDrawer = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentByTag("NavigationDrawerFragment");
        if (!shouldShowDualPane) {
            setupActionBar();
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
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle(title);
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
