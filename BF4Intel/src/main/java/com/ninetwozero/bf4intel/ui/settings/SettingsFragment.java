package com.ninetwozero.bf4intel.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.ninetwozero.bf4intel.R;

public class SettingsFragment extends PreferenceFragment {
    public static SettingsFragment newInstance(final Bundle bundle) {
        final SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public SettingsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main);
    }
}
