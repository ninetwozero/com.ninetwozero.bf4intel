package com.ninetwozero.bf4intel.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseListFragment;
import com.ninetwozero.bf4intel.datatypes.ListRow;
import com.ninetwozero.bf4intel.datatypes.ListRowType;
import com.ninetwozero.bf4intel.factories.ListRowFactory;
import com.ninetwozero.bf4intel.ui.adapters.ListRowAdapter;

import java.util.ArrayList;
import java.util.List;

public class SoldierStatsFragment extends BaseListFragment {
    public SoldierStatsFragment() {
    }


    public static SoldierStatsFragment newInstance(final Bundle data) {
        final SoldierStatsFragment fragment = new SoldierStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle state) {
        super.onCreateView(inflater, parent, state);
        final View view = this.layoutInflater.inflate(R.layout.fragment_soldier_stats, parent, false);
        initialize(view);
        return view;
    }

    private void initialize(final View view) {
        final ListRowAdapter slidingMenuAdapter = new ListRowAdapter(getItemsForMenu(), getActivity());
        // TODO: setListAdapter(slidingMenuAdapter);

        final Bundle arguments = getArguments();
        if( arguments != null ) {
            ((TextView) view.findViewById(R.id.internal_title)).setText(arguments.getString("TEST"));
        }

    }

    private List<ListRow> getItemsForMenu() {
        final List<ListRow> items = new ArrayList<ListRow>();
        items.add(ListRowFactory.create(ListRowType.HEADING, "SOLDIERS"));
        return items;
    }
}
