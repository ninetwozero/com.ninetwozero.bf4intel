package com.ninetwozero.bf4intel.database.dao.assignments;

import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AssignmentSorter {
    private static final List<String> ASSIGNMENT_TYPE = new ArrayList<String>(Arrays.asList("bronze", "silver", "gold", "sp"));

    public static SortedAssignmentContainer sort(final Assignments assignments) {
        return assignments != null ? fetchSortedAssignments(assignments) : new SortedAssignmentContainer();
    }

    private static SortedAssignmentContainer fetchSortedAssignments(final Assignments assignments) {
        List<Assignment> orderedAssignments = new ArrayList<Assignment>();
        Map<String, List<String>> missions = assignments.getAssignmentCategory();
        for (String assignmentType : ASSIGNMENT_TYPE) {
            List<String> groupedAssignments = missions.get(assignmentType);
            orderedAssignments.addAll(
                fetchGroupedAssignments(
                    assignments.getAssignments(),
                    groupedAssignments
                )
            );
        }
        return new SortedAssignmentContainer(orderedAssignments, assignments.getExpansions());
    }

    private static List<Assignment> fetchGroupedAssignments(final Map<String, Assignment> assignments, final List<String> groupedAssignments) {
        List<Assignment> orderedGroup = new ArrayList<Assignment>();
        for (String key : groupedAssignments) {
            if (assignments.containsKey(key)) {
                orderedGroup.add(assignments.get(key));
            }
        }
        return orderedGroup;
    }
}
