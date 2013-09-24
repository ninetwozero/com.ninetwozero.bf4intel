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

public class PlatoonProfileFragment extends AbstractListFragment {
    public PlatoonProfileFragment() {
    }

    public static PlatoonProfileFragment newInstance() {
        final PlatoonProfileFragment fragment = new PlatoonProfileFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);

        final View view = mInflater.inflate(R.layout.fragment_platoon_profile, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        final ListRowAdapter slidingMenuAdapter = new ListRowAdapter(getActivity(), getItemsForMenu());
        setListAdapter(slidingMenuAdapter);

        updateActionBar(getActivity(), "Chili-powered Zebras", R.drawable.test_platoon);
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.HEADING, "BASIC INFORMATION"));
        items.add(ListRowFactory.create(ListRowType.PROFILE_SOLDIER, new Bundle()));
        items.add(ListRowFactory.create(ListRowType.HEADING, "PRESENTATION"));
        items.add(ListRowFactory.create(ListRowType.PROFILE_PLATOON, new Bundle()));
        return items;
    }
}
