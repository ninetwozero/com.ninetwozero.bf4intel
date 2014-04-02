package com.ninetwozero.bf4intel.events.assignments;

import com.ninetwozero.bf4intel.events.RefreshCompletedEvent;

public class AssignmentsRefreshedEvent extends RefreshCompletedEvent {
    public AssignmentsRefreshedEvent(final boolean success) {
        super(success);
    }
}
