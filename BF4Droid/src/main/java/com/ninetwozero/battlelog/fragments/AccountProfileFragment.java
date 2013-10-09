package com.ninetwozero.battlelog.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.abstractions.AbstractListFragment;
import com.ninetwozero.battlelog.adapters.ListRowAdapter;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;
import com.ninetwozero.battlelog.factories.ListRowFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountProfileFragment extends AbstractListFragment {
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

        final View view = mInflater.inflate(R.layout.generic_list, parent, false);
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
