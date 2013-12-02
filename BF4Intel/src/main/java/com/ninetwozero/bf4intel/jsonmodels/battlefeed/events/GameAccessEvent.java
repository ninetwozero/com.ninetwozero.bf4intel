package com.ninetwozero.bf4intel.jsonmodels.battlefeed.events;

import android.view.View;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;

import java.util.HashMap;
import java.util.Map;

public class GameAccessEvent extends BaseEvent {
    private final String GAME_NAME = "Battlefield 4";
    private final Map<Integer, String> expansionMap = new HashMap<Integer, String>() {{
        put(1048576, "China Rising");
    }};

    private int game;
    private int expansion;
    private int platform;

    public GameAccessEvent(final EventType eventType, final int game, final int platform, final int expansion) {
        super(eventType);
        this.game = game;
        this.platform = platform;
        this.expansion = expansion;
    }

    public int getGame() {
        return game;
    }

    public int getPlatform() {
        return this.platform;
    }

    public int getExpansion() {
        return expansion;
    }

    public String getFullTitle() {
        final StringBuilder builder = new StringBuilder().append(GAME_NAME);
        if (expansion > 0 ) {
            builder.append(": ").append(expansionMap.get(expansion));
        }
        return builder.toString();
    }

    @Override
    public void populateEventSpecificData(final View view) {
        ((TextView) view.findViewById(R.id.game_name)).setText(getFullTitle());
    }
}
