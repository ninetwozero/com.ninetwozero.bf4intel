package com.ninetwozero.bf4intel.database.dao.assignments;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AssignmentSorter extends AbstractSorter<SortedAssignmentContainer> {
    public static final List<String> ASSIGNMENT_TYPE = new ArrayList<String>(Arrays.asList("bronze", "silver", "gold", "sp"));
    private final Assignments assignments;
    private final SortMode mode;

    public AssignmentSorter(final Assignments assignments, final SortMode mode) {
        this.assignments = assignments;
        this.mode = mode;
    }

    @Override
    public SortedAssignmentContainer sort() {
        if (mode == SortMode.PROGRESS) {
            return sortByProgress();
        } else {
            return sortByCategory();
        }
    }

    @Override
    protected SortedAssignmentContainer sortByProgress() {
        List<Assignment> uncompleted = fetchUncompletedAssignments();
        Collections.sort(uncompleted);
        return new SortedAssignmentContainer(uncompleted, assignments.getExpansions());
    }

    @Override
    protected SortedAssignmentContainer sortByCategory() {
        List<Assignment> orderedAssignments = new ArrayList<Assignment>();
        Map<String, List<String>> missions = assignments.getAssignmentCategory();
        for (String group : ASSIGNMENT_TYPE) {
            List<String> groupedAssignments = missions.get(group);
            orderedAssignments.addAll(
                fetchGroupedAssignments(groupedAssignments, group)
            );
        }
        return new SortedAssignmentContainer(orderedAssignments, assignments.getExpansions());
    }

    private List<Assignment> fetchGroupedAssignments(final List<String> assignmentsInGroup, final String group) {
        List<Assignment> orderedGroup = new ArrayList<Assignment>();
        for (String key : assignmentsInGroup) {
            if (assignments.getAssignments().containsKey(key)) {
                Assignment assignment = assignments.getAssignments().get(key);
                assignment.setGroup(group);
                orderedGroup.add(assignment);
            }
        }
        return orderedGroup;
    }

    private List<Assignment> fetchUncompletedAssignments() {
        List<Assignment> allAssignments = new ArrayList<Assignment>(assignments.getAssignments().values());
        List<Assignment> uncompleted = new ArrayList<Assignment>();
        for (Assignment assignment : allAssignments) {
            if (assignment.getCompletion() != 100) {
                uncompleted.add(assignment);
            }
        }
        return uncompleted;
    }
}
