package com.ninetwozero.bf4intel.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.BaseFragment;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AssignmentsFragment extends BaseFragment {

    private static final List<String> ASSIGNMENT_TYPES = new ArrayList<String>(Arrays.asList("bronze", "silver", "gold", "sp"));

    private Assignments assignments;
    private GridView gridView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
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
    public void onAssignmentsLoaded(final Assignments assignments) {
        this.assignments = assignments;
        setupGrid();
    }

    private void setupGrid() {
        AssignmentsAdapter adapter = new AssignmentsAdapter(getAssignments(), getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<Assignment> getAssignments() {
        return assignments != null ? getSortedAssignments() : new ArrayList<Assignment>();
    }

    private List<Assignment> getSortedAssignments() {
        List<Assignment> orderedAssignments = new ArrayList<Assignment>();
        Map<String, List<String>> missions = assignments.getAssignmentCategory();
        for(String assignmentType : ASSIGNMENT_TYPES) {
            List<String> groupedAssignments = missions.get(assignmentType);
            Collections.sort(groupedAssignments);
            orderedAssignments.addAll(cherryPickAssignments(groupedAssignments));
        }
        return orderedAssignments;
    }

    /* TODO: Not clear what this code is doing */
    private List<Assignment> cherryPickAssignments(final List<String> groupedAssignments) {
        List<Assignment> orderedGroup = new ArrayList<Assignment>();
        for(String key: groupedAssignments) {
            if(assignments.getAssignments().containsKey(key)) {
                orderedGroup.add(assignments.getAssignments().get(key));
            }
        }
        return orderedGroup;
    }
}
