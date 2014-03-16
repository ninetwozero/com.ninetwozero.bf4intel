package com.ninetwozero.bf4intel.ui.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.ui.BaseLoadingFragment;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.network.SimpleGetRequest;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.menu.RefreshEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AssignmentGridFragment extends BaseLoadingFragment {
    private static final List<String> ASSIGNMENT_TYPE = new ArrayList<String>(Arrays.asList("bronze", "silver", "gold", "sp"));

    public static AssignmentGridFragment newInstance(final Bundle data) {
        final AssignmentGridFragment fragment = new AssignmentGridFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_assignments, container, false);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent event) {
        startLoadingData();
    }

    @Override
    protected void startLoadingData() {
        showLoadingState(true);
        final Bundle bundle = getArguments();
        requestQueue.add(
            new SimpleGetRequest<List<Assignment>>(
                UrlFactory.buildAssignmentsURL(
                    bundle.getString(Keys.Soldier.NAME),
                    bundle.getLong(Keys.Soldier.ID),
                    bundle.getString(Keys.Profile.ID),
                    bundle.getInt(Keys.Soldier.PLATFORM)
                ),
                this
            ) {
                @Override
                protected List<Assignment> doParse(String json) {
                    final Assignments assignments = fromJson(json, Assignments.class);
                    return processAssignments(assignments);
                }

                @Override
                protected void deliverResponse(List<Assignment> response) {
                    setupGrid(response);
                    showLoadingState(false);
                }
            }
        );
    }

    private List<Assignment> processAssignments(final Assignments assignments) {
        return assignments != null ? fetchSortedAssignments(assignments) : new ArrayList<Assignment>();
    }

    private List<Assignment> fetchSortedAssignments(final Assignments assignments) {
        List<Assignment> orderedAssignments = new ArrayList<Assignment>();
        Map<String, List<String>> missions = assignments.getAssignmentCategory();
        for(String assignmentType : ASSIGNMENT_TYPE) {
            List<String> groupedAssignments = missions.get(assignmentType);
            orderedAssignments.addAll(fetchGroupedAssignments(assignments, groupedAssignments));
        }
        return orderedAssignments;
    }

    private List<Assignment> fetchGroupedAssignments(final Assignments assignments, final List<String> groupedAssignments) {
        List<Assignment> orderedGroup = new ArrayList<Assignment>();
        for(String key: groupedAssignments) {
            if(assignments.getAssignments().containsKey(key)) {
                orderedGroup.add(assignments.getAssignments().get(key));
            }
        }
        return orderedGroup;
    }

    private void setupGrid(final List<Assignment> assignments) {
        final View view = getView();
        if (view == null) {
            return;
        }

        final GridView gridView = (GridView) view.findViewById(R.id.assignments_grid);
        gridView.setAdapter(new AssignmentsAdapter(getActivity(), assignments));
    }
}
