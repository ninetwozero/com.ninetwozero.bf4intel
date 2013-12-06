package com.ninetwozero.bf4intel.json.battlefeed.events;

import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;

public class CommentedGameReportEvent extends BaseEvent {
    private final String id;
    private final String server;
    private final String map;
    private final int mapVariant;
    private final int gameMode;
    private final String comment;

    public CommentedGameReportEvent(
        final EventType eventType, final String id, final String server,
        final String map, final int mapVariant, final int gameMode,
        final String comment
    ) {
        super(eventType);
        this.id = id;
        this.server = server;
        this.map = map;
        this.mapVariant = mapVariant;
        this.gameMode = gameMode;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getServer() {
        return server;
    }

    public String getMap() {
        return map;
    }

    public int getMapVariant() {
        return mapVariant;
    }

    public int getGameMode() {
        return gameMode;
    }

    public String getComment() {
        return comment;
    }
}
