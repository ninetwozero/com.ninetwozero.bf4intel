package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;

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

    }
}
