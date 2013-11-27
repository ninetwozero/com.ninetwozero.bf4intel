package com.ninetwozero.bf4intel.json.assignments;

public enum AssignmentPrerequirement {
    MISSION("AwardGroup_Mission"),
    RANK("AwardGroup_Ranks"),
    NONE("single_player");
    private final String prerequirement;

    private AssignmentPrerequirement(String prerequirement) {
        this.prerequirement = prerequirement;
    }

    public boolean equals(String compareValue) {
        return prerequirement.equals(compareValue);
    }

    @Override
    public String toString() {
        return prerequirement;
    }
}
