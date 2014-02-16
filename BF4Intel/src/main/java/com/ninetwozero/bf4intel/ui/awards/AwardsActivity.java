package com.ninetwozero.bf4intel.ui.awards;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseIntelActivity;
import com.ninetwozero.bf4intel.factories.FragmentFactory;

public class AwardsActivity extends BaseIntelActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        setupFragment();
    }

    private void setupFragment() {
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(
            R.id.activity_container,
            FragmentFactory.get(
                FragmentFactory.Type.SOLDIER_AWARDS,
                getIntent().getBundleExtra("profile")
            )
        ).commit();
    }
}
