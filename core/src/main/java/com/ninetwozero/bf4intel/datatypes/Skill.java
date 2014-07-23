package com.ninetwozero.bf4intel.datatypes;

public class Skill {
    private int stringResource;
    private String value;

    public Skill(final int stringResource, final Object value) {
        this.stringResource = stringResource;
        this.value = String.valueOf(value);
    }

    public int getStringResource() {
        return stringResource;
    }

    public String getValue() {
        return value;
    }
}
