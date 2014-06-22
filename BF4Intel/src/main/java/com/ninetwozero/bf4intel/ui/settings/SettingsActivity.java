package com.ninetwozero.bf4intel.ui.settings;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/* Unfortunately, this class is needed due to the lack of a support.v4 PreferenceFragment */

public class SettingsActivity extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, SettingsFragment.newInstance(new Bundle()));
        transaction.commit();

    }
}
