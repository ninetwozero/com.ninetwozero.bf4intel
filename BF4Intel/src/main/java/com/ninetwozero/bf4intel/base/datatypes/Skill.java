package com.ninetwozero.bf4intel.base.datatypes;

public class Skill {
    private int mStringResource;
    private String mValue;

    public Skill(final int stringResource, final Object value) {
        mStringResource = stringResource;
        mValue = String.valueOf(value);
    }

    public int getStringResource() {
        return mStringResource;
    }

    public String getValue() {
        return mValue;
    }
}
