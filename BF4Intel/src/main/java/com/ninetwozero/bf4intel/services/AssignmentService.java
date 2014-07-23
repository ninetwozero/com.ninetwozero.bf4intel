package com.ninetwozero.bf4intel.services;

import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentSorter;
import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.events.assignments.AssignmentsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.resources.Keys;
import com.ninetwozero.bf4intel.ui.assignments.AssignmentGridFragment;

import java.net.URL;

public class AssignmentService extends BaseDaoService<AssignmentsDAO, AssignmentsRefreshedEvent> {

    @Override
    protected AssignmentsRefreshedEvent getEventToBroadcast(boolean result) {
        return new AssignmentsRefreshedEvent(result);
    }

    @Override
    protected AssignmentsDAO parseJsonIntoDao(String json) {
        final Assignments assignments = fromJson(json, Assignments.class);
        final SortMode sortMode = SortMode.fromOrdinal(sharedPreferences.getInt(AssignmentGridFragment.ASSIGNMENT_SORT_MODE, 0));
        return new AssignmentsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            new AssignmentSorter(assignments).sort(sortMode)
        );
    }

    @Override
    protected URL getUrlForService() {
        return UrlFactory.buildAssignmentsURL(
            soldier.getString(Keys.Soldier.NAME, ""),
            soldier.getString(Keys.Soldier.ID, ""),
            soldier.getString(Keys.Profile.ID, ""),
            soldier.getInt(Keys.Soldier.PLATFORM, 0)
        );
    }
}