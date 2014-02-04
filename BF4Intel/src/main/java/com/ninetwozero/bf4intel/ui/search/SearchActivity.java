package com.ninetwozero.bf4intel.ui.search;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

public class SearchActivity extends BaseIntelActivity {
    public static final int REQUEST_SEARCH = 0;
    public static final String QUERY = SearchManager.QUERY;
    public static final String RESULT_SEARCH_RESULT = "searchResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        initialize(getIntent());
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        final Bundle data = getBundleFromIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize(final Intent intent) {
        setupActionBar();
        setupFragment();
    }

    private void setupActionBar() {
        final ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        if (getCallingActivity() == null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void setupFragment() {
        final Bundle data = getBundleFromIntent(getIntent());
        final Fragment fragment = FragmentFactory.get(FragmentFactory.Type.PROFILE_SEARCH, data);
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_root, fragment, "ProfileSearchFragment");
        transaction.commit();
    }

    private Bundle getBundleFromIntent(final Intent intent) {
        final Bundle bundle = new Bundle();
        final String query = intent.getStringExtra(QUERY);

        if (query == null || query.equals("")) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        bundle.putString(ProfileSearchFragment.INTENT_SEARCH_RESULT, query);
        return bundle;
    }

}
