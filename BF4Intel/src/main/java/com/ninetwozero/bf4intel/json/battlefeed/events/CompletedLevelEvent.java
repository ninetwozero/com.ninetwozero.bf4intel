package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;

public class CompletedLevelEvent extends BaseEvent {
    private final String levelName;
    private final int gameType;
    private final String difficulty;
    private final Profile partner;

    public CompletedLevelEvent(final EventType eventType, final String levelName, final int gameType, final String difficulty, final Profile partner) {
        super(eventType);
        this.levelName = levelName;
        this.gameType = gameType;
        this.difficulty = difficulty;
        this.partner = partner;
    }

    public String getLevelName() {
        return levelName;
    }

    public int getGameType() {
        return gameType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Profile getPartner() {
        return partner;
    }

    public boolean hasPartner() {
        return partner != null;
    }
}
