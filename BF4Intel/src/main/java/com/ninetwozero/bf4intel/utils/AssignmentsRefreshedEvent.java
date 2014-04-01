package com.ninetwozero.bf4intel.utils;

public class AssignmentsRefreshedEvent {
    private boolean success;
    public AssignmentsRefreshedEvent(final boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
