
package com.ninetwozero.bf4intel.ui.about;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;

public class AppInfoFragment extends Fragment {

    public static AppInfoFragment newInstance() {
        final AppInfoFragment fragment = new AppInfoFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public AppInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        setupVersion(view);
        return view;
    }

    private void setupVersion(final View view) {
        final Activity activity = getActivity();
        String versionNumber = "Unknown";

        if (activity != null) {
            try {
                final PackageManager manager = getActivity().getPackageManager();
                if (manager != null) {
                    versionNumber = manager.getPackageInfo(activity.getPackageName(), 0).versionName;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        ((TextView) view.findViewById(R.id.version)).setText(versionNumber);
    }
}
