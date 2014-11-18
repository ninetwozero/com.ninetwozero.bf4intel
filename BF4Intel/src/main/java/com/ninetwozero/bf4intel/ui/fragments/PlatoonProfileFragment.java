package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;

public class PlatoonProfileFragment extends BaseListFragment {
    public PlatoonProfileFragment() {
    }


    public static PlatoonProfileFragment newInstance(final Bundle data) {
        final PlatoonProfileFragment fragment = new PlatoonProfileFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = this.layoutInflater.inflate(R.layout.fragment_platoon_profile, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
    }
}
