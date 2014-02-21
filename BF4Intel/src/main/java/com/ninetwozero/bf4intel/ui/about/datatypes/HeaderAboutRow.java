package com.ninetwozero.bf4intel.ui.about.datatypes;

public class HeaderAboutRow extends BaseAboutRow {
    private int childCount;

    public HeaderAboutRow(final int title, final int childCount) {
        super(title, Type.HEADER);
        this.childCount = childCount;
    }

    public int getChildCount() {
        return childCount;
    }
}
