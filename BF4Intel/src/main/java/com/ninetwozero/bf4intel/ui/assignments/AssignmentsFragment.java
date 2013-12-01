package com.ninetwozero.bf4intel.ui.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.squareup.otto.Subscribe;

import java.util.*;

public class AssignmentsFragment extends BaseFragment {

    private static final List<String> ASSIGNMENT_TYPE = new ArrayList<String>(Arrays.asList("bronze", "silver", "gold", "sp"));

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
        setupGrid();
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onAssignmentsLoaded(Assignments assignments) {
        this.assignments = assignments;
        setupGrid();
    }

    private void setupGrid() {
        AssignmentsAdapter adapter = new AssignmentsAdapter(getAssignments(), getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<Assignment> getAssignments() {
        return assignments != null ? fetchSortedAssignments() : new ArrayList<Assignment>();
    }

    private List<Assignment> fetchSortedAssignments() {
        List<Assignment> orderedAssignments = new ArrayList<Assignment>();
        Map<String, List<String>> missions = assignments.getAssignmentCategory();
        for(String assignmentType : ASSIGNMENT_TYPE) {
            List<String> groupedAssignments = missions.get(assignmentType);
            Collections.sort(groupedAssignments);
            orderedAssignments.addAll(fetchGroupedAssignments(groupedAssignments));
        }
        return orderedAssignments;
    }

    private List<Assignment> fetchGroupedAssignments(List<String> groupedAssignments) {
        List<Assignment> orderedGroup = new ArrayList<Assignment>();
        for(String key: groupedAssignments) {
            if(assignments.getAssignments().containsKey(key)) {
                orderedGroup.add(assignments.getAssignments().get(key));
            }
        }
        return orderedGroup;
    }
}
