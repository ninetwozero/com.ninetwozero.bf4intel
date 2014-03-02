
package com.ninetwozero.bf4intel.ui.about;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;

public class AboutFragment extends BaseFragment implements View.OnClickListener {
    private static final String URL_GPLUS = "https://plus.google.com/communities/116943801107614500778";
    private static final String URL_TWITTER = "https://twitter.com/intent/tweet?text=%40karllindmark+%40peter_budo";

    public static AboutFragment newInstance() {
        final AboutFragment fragment = new AboutFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public AboutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        setupButtons(view);
        setupVersion(view);
    }

    private void setupButtons(final View view) {
        view.findViewById(R.id.button_email).setOnClickListener(this);
        view.findViewById(R.id.button_gplus).setOnClickListener(this);
        view.findViewById(R.id.button_tweet).setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_email:
                intent = new Intent(Intent.ACTION_SENDTO).setData(
                    Uri.parse("mailto:" + getString(R.string.app_email))
                );
                break;
            case R.id.button_gplus:
                intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(URL_GPLUS));
                break;
            case R.id.button_tweet:
                intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(URL_TWITTER));
                break;
        }
        if (null != intent) {
            getActivity().startActivity(intent);
        }
    }
}
