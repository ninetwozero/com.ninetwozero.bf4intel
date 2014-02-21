package com.ninetwozero.bf4intel.ui.about.datatypes;

public class BaseAboutRow {
    protected final int title;
    protected final Type type;

    public BaseAboutRow(final int title, final Type type) {
        this.title = title;
        this.type = type;
    }

    public int getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        HEADER,
        ITEM
    }
}
