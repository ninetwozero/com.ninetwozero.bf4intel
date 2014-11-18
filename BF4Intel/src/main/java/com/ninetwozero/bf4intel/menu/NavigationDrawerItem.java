package com.ninetwozero.bf4intel.menu;

public class NavigationDrawerItem {
    public static final int NO_ICON = -1;

    private Type type;
    private int title;
    private int icon;

    public NavigationDrawerItem(final Type type) {
        this.type = type;
        this.icon = NO_ICON;
    }

    public NavigationDrawerItem(final Type type, final int title) {
        this.type = type;
        this.title = title;
        this.icon = NO_ICON;
    }

    // For future purposes, if we want to use icons in the NavigationDrawer
    @SuppressWarnings("unused")
    public NavigationDrawerItem(final Type type, final int title, final int icon) {
        this.type = type;
        this.title = title;
        this.icon = icon;
    }

    public Type getType() {
        return type;
    }

    public int getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public enum Type {
        SEPARATOR,
        HEADING,

        HOME,
        NEWS,

        OVERVIEW,
        STATISTICS,
        UNLOCKS,
        ASSIGNMENTS,
        AWARDS,

        ABOUT,
        SETTINGS,
        SEARCH,
        BATTLE_CHAT
    }
}
