package com.ninetwozero.bf4intel.services;

import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentSorter;
import com.ninetwozero.bf4intel.database.dao.assignments.AssignmentsDAO;
import com.ninetwozero.bf4intel.events.assignments.AssignmentsRefreshedEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.resources.Keys;

import java.net.URL;

public class AssignmentService extends BaseDaoService<AssignmentsDAO, AssignmentsRefreshedEvent> {

    @Override
    protected AssignmentsRefreshedEvent getEventToBroadcast(boolean result) {
        return new AssignmentsRefreshedEvent(result);
    }

    @Override
    protected AssignmentsDAO parseJsonIntoDao(String json) {
        final Assignments assignments = fromJson(json, Assignments.class);
        return new AssignmentsDAO(
            soldier.getString(Keys.Soldier.ID),
            soldier.getString(Keys.Soldier.NAME),
            soldier.getInt(Keys.Soldier.PLATFORM),
            AssignmentSorter.sort(assignments)
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