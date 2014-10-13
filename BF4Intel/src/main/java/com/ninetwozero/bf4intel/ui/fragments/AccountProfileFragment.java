package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;

public class AccountProfileFragment extends BaseListFragment {
    public AccountProfileFragment() {
    }

    public static AccountProfileFragment newInstance(final Bundle data) {
        final AccountProfileFragment fragment = new AccountProfileFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = this.layoutInflater.inflate(R.layout.generic_list, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        updateActionBar(getActivity(), "NINETWOZERO", R.drawable.test_gravatar);
    }
}
