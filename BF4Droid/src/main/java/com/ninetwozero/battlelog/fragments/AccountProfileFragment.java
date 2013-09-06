package com.ninetwozero.battlelog.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.adapters.ListRowAdapter;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;
import com.ninetwozero.battlelog.factories.ListRowFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountProfileFragment extends ListFragment {
    private LayoutInflater mInflater;

    public AccountProfileFragment() {}

    public static AccountProfileFragment newInstance() {
        final AccountProfileFragment fragment = new AccountProfileFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        mInflater = inflater;

        final View view = mInflater.inflate(R.layout.generic_list, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        final ListRowAdapter slidingMenuAdapter = new ListRowAdapter(getActivity(), getItemsForMenu());
        setListAdapter(slidingMenuAdapter);
    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();

        items.add(ListRowFactory.create(ListRowType.PROFILE_ACCOUNT, new Bundle()));

        items.add(ListRowFactory.create(ListRowType.HEADING, "SOLDIERS"));
        for( int i = 0, numSoldiers = 3; i < numSoldiers; i++ ) {
            items.add(ListRowFactory.create(ListRowType.PROFILE_SOLDIER, new Bundle()));
        }

        items.add(ListRowFactory.create(ListRowType.HEADING, "PLATOONS"));
        for( int i = 0, numSoldiers = 3; i < numSoldiers; i++ ) {
            items.add(ListRowFactory.create(ListRowType.PROFILE_PLATOON, new Bundle()));
        }
        return items;
    }
}
