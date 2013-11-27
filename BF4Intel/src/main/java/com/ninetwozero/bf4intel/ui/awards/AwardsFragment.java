package com.ninetwozero.bf4intel.ui.awards;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.json.awards.Awards;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class AwardsFragment extends Fragment {

    private Awards awards;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);
        gridView = (GridView) view.findViewById(R.id.assignments_grid);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        setupGrid();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void awards(Awards awards) {
        this.awards = awards;
        setupGrid();
    }

    private void setupGrid() {
        AwardsAdapter adapter = new AwardsAdapter(awards(), getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<Awards> awards() {
        return /*awards != null ? orderAssignments() :*/ new ArrayList<Awards>();
    }
}
