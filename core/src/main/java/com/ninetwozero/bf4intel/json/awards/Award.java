package com.ninetwozero.bf4intel.json.awards;

public class Award {
    private final String medalCode;
    private final Medal medal;
    private final String ribbonCode;
    private final Ribbon ribbon;

    public Award(String medalCode, Medal medal, String ribbonCode, Ribbon ribbon) {

        this.medalCode = medalCode;
        this.medal = medal;
        this.ribbonCode = ribbonCode;
        this.ribbon = ribbon;
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
}
