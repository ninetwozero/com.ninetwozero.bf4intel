package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;

public class HomeFragment extends BaseFragment {
    public static HomeFragment newInstance(final Bundle data) {
        final HomeFragment fragment = new HomeFragment();
        fragment.setArguments(data);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        final View baseView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(baseView, savedState);
        return baseView;
    }

    private void initialize(final View view, final Bundle state) {
        view.findViewById(R.id.button_select_account).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Activity activity = getActivity();
                    activity.startActivityForResult(
                        new Intent(activity, LoginActivity.class),
                        LoginActivity.REQUEST_LOGIN
                    );
                }
            }
        );
    }
}
