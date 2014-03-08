package com.ninetwozero.bf4intel.json.assignments;

public enum AssignmentPrerequisite {
    MISSION("AwardGroup_Mission"),
    RANK("AwardGroup_Ranks"),
    NONE("single_player");
    private final String prerequisite;

    private AssignmentPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public boolean equals(String compareValue) {
        return prerequisite.equals(compareValue);
    }

    @Override
    public String toString() {
        return prerequisite;
    }
}
