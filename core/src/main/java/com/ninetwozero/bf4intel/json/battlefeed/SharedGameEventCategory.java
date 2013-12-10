package com.ninetwozero.bf4intel.json.battlefeed;

public enum SharedGameEventCategory {
    AWARDS("statItems"),
    ASSIGNMENTS("statItems"),
    DOGTAGS("dogtags"),
    GAME_REPORT("statItems"),
    RANKUP(""),
    TRACKING_COMPLETE(""),
    UNKNOWN("");

    private final String arrayKey;
    SharedGameEventCategory(final String arrayKey) {
        this.arrayKey = arrayKey;
    }

    public String getArrayKey() {
        return arrayKey;
    }
}