package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapters.ListRowAdapter;
import com.ninetwozero.bf4intel.base.app.BaseListFragment;
import com.ninetwozero.bf4intel.base.datatypes.ListRow;
import com.ninetwozero.bf4intel.base.datatypes.ListRowType;
import com.ninetwozero.bf4intel.base.factories.ListRowFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountProfileFragment extends BaseListFragment {
    public AccountProfileFragment() {
    }

    public static AccountProfileFragment newInstance() {
        final AccountProfileFragment fragment = new AccountProfileFragment();
        fragment.setArguments(new Bundle());
        return fragment;
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
        final ListRowAdapter slidingMenuAdapter = new ListRowAdapter(getActivity(), getItems());
        setListAdapter(slidingMenuAdapter);

        updateActionBar(getActivity(), "NINETWOZERO", R.drawable.test_gravatar);
    }

    private List<ListRow> getItems() {
        final List<ListRow> items = new ArrayList<ListRow>();

        items.add(ListRowFactory.create(ListRowType.PROFILE_ACCOUNT, new Bundle()));

        items.add(ListRowFactory.create(ListRowType.HEADING, "SOLDIERS"));
        for (int i = 0, numSoldiers = 3; i < numSoldiers; i++) {
            items.add(ListRowFactory.create(ListRowType.PROFILE_SOLDIER, new Bundle()));
        }

        items.add(ListRowFactory.create(ListRowType.HEADING, "PLATOONS"));
        for (int i = 0, numSoldiers = 3; i < numSoldiers; i++) {
            items.add(ListRowFactory.create(ListRowType.PROFILE_PLATOON, new Bundle()));
        }
        return items;
    }
}
