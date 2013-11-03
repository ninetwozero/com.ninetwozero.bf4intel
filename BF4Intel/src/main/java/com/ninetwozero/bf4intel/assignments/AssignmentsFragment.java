package com.ninetwozero.bf4intel.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);
        setGrid(R.id.assignments_grid, view);
        return view;
    }

    private void setGrid(int gridId, View parent) {
        GridView gridView = (GridView) parent.findViewById(gridId);
        AssignmentsAdapter adapter = new AssignmentsAdapter(assignments(), getActivity().getApplicationContext());
        gridView.setAdapter(adapter);
    }

    private List<Assignment> assignments() {
        List<Assignment> assignments = new ArrayList<Assignment>();
        assignments.add(new Assignment(50, false, R.drawable.as01a, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as02, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as03, AssignmentPrerequirement.MISSION.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as04, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as05, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as06, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as07, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as08, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as09, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as10, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as11, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as12, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as13, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as14, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as15, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as16, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as17, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as18, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as19, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as20, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as21, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as22, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as23, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as24, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as25, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as26, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as27, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as28, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as29, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as30, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as31, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as32, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as33, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as34, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as35, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as36, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as37, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as38, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as39, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as40, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as41, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as42, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(50, false, R.drawable.as43, AssignmentPrerequirement.RANK));
        assignments.add(new Assignment(100, true, R.drawable.as44, AssignmentPrerequirement.MISSION));
        assignments.add(new Assignment(100, true, R.drawable.as45, AssignmentPrerequirement.MISSION));

        return assignments;
    }
}
