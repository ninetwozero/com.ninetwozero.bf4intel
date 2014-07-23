package com.ninetwozero.bf4intel.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

public class SingleFragmentActivity extends BaseIntelActivity {
    public static final String INTENT_FRAGMENT_TYPE = "fragmentType";
    public static final String INTENT_FRAGMENT_DATA = "fragmentData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        initialize(getIntent());
    }

    private void initialize(final Intent intent) {
        setupActionBar();
        loadFragmentFromIntent(intent);
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadFragmentFromIntent(final Intent intent) {
        final int ordinal = intent.getIntExtra(INTENT_FRAGMENT_TYPE, 0);
        final Bundle data = intent.getBundleExtra(INTENT_FRAGMENT_DATA);
        final Fragment fragment = FragmentFactory.fromOrdinal(ordinal, data);

        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity_root, fragment, "SingleItemFragment");
        transaction.commit();
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

}
