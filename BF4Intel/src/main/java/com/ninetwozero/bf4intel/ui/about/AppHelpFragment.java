
package com.ninetwozero.bf4intel.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;

public class AppHelpFragment extends Fragment implements View.OnClickListener {
    private static final String URL_GPLUS = "https://plus.google.com/+BF4Intel";
    private static final String URL_TWITTER = "https://twitter.com/bf4intel";

    public static AppHelpFragment newInstance() {
        final AppHelpFragment fragment = new AppHelpFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public AppHelpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_about_app_help, container, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        view.findViewById(R.id.button_email).setOnClickListener(this);
        view.findViewById(R.id.button_gplus).setOnClickListener(this);
        view.findViewById(R.id.button_tweet).setOnClickListener(this);
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
