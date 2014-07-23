package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentPrerequisite implements Serializable {
    @SerializedName("stringID")
    private String key;
    @SerializedName("code")
    private String code;
    @SerializedName("group")
    private String group;
    @SerializedName("timesTaken")
    private int timesTaken;

    public AssignmentPrerequisite(String key, String code, String group, int timesTaken) {
        this.key = key;
        this.code = code;
        this.group = group;
        this.timesTaken = timesTaken;
    }

    public String getKey() {
        return key;
    }

    public String getCode() {
        return code;
    }

    public String getGroup() {
        return group;
    }

    public int getTimesTaken() {
        return timesTaken;
    }

    public static enum Type {
        EXPANSION("AwardGroup_Expansion"),
        MISSION("AwardGroup_Mission"),
        RANK("AwardGroup_Ranks"),
        NONE("single_player");

        private final String group;

        private Type(String group) {
            this.group = group;
        }

        public String getGroup() {
            return group;
        }

        public boolean equals(String compareValue) {
            return group.equals(compareValue);
        }

        @Override
        public String toString() {
            return group;
        }

        public static Type from(String group) {
            for (Type type : values()) {
                if (type.group.equalsIgnoreCase(group)) {
                    return type;
                }
            }
            throw new IllegalStateException("Invalid AssignmentPrerequisite.Type group: " + group);
        }
    }
}
