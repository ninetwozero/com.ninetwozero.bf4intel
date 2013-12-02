package com.ninetwozero.bf4intel.json.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.Profile;
import com.ninetwozero.bf4intel.resources.maps.LevelStringMap;

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

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.level)).setText(LevelStringMap.get(levelName));
        ((TextView) view.findViewById(R.id.difficulty)).setText(difficulty);
        if (hasPartner()) {
            ((TextView) view.findViewById(R.id.partner)).setText(partner.getUsername());
            view.findViewById(R.id.wrap_partner).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.wrap_partner).setVisibility(View.GONE);
        }
    }
}
