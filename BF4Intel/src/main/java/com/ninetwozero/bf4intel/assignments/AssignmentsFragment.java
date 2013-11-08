package com.ninetwozero.bf4intel.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsFragment extends Fragment {

    private Assignments assignments;
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
        setGrid();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void assignments(Assignments assignments) {
        this.assignments = assignments;
        setGrid();
    }

    private void setGrid() {
        AssignmentsAdapter adapter = new AssignmentsAdapter(assignments(), getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<Assignment> assignments() {
        return assignments != null ? new ArrayList<Assignment>(assignments.getAssignments().values()) : new ArrayList<Assignment>();
    }
}
