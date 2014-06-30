package com.ninetwozero.bf4intel.json.awards;

import java.io.Serializable;

public class Award implements Comparable<Award>, Serializable {
    private final String medalCode;
    private final Medal medal;
    private final String ribbonCode;
    private final Ribbon ribbon;
    private final String category;

    public Award(String medalCode, Medal medal, String ribbonCode, Ribbon ribbon, String category) {
        this.medalCode = medalCode;
        this.medal = medal;
        this.ribbonCode = ribbonCode;
        this.ribbon = ribbon;
        this.category = category;
    }

    public String getMedalCode() {
        return medalCode;
    }

    public Medal getMedal() {
        return medal;
    }

    public String getRibbonCode() {
        return ribbonCode;
    }

    public Ribbon getRibbon() {
        return ribbon;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int compareTo(final Award o) {
        return medal.compareTo(o.getMedal());
    }
}
