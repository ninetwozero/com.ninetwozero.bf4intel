package com.ninetwozero.bf4intel.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.datatypes.TrackingNewProfileEvent;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.about.AppInfoActivity;
import com.ninetwozero.bf4intel.ui.fragments.NavigationDrawerFragment;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;

import java.util.Locale;

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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.ab_action_search);
        final MenuItem refreshMenuItem = menu.findItem(R.id.ab_action_refresh);
        if (refreshMenuItem != null) {
            refreshMenuItem.setVisible(false);
        }
        if (searchMenuItem != null) {
            searchMenuItem.setVisible(true);
        }

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView abSearchView = (SearchView) searchMenuItem.getActionView();
        if (abSearchView != null) {
            abSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            abSearchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(final String s) {
                        removeSearchView(abSearchView);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(final String s) {
                        return false;
                    }

                    private void removeSearchView(final SearchView searchView) {
                        searchView.setQuery("", false);
                        searchView.clearFocus();
                        searchView.onActionViewCollapsed();
                    }
                }
            );
        }
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

            case R.id.ab_action_select_user:
                startActivityForResult(
                    new Intent(this, LoginActivity.class), LoginActivity.REQUEST_PROFILE
                );
                return true;

            case R.id.ab_action_about:
                startActivity(new Intent(this, AppInfoActivity.class));
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
        this.title = title == null? this.title : title.toUpperCase(Locale.getDefault());
        if (drawerLayout != null && !isRecreated) {
            toggleNavigationDrawer(false);
        }
        isRecreated = false;
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

    private void setupNavigationDrawer() {
        navigationDrawer = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
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
