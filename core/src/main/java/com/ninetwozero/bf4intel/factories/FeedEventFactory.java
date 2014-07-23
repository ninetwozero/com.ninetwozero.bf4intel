package com.ninetwozero.bf4intel.factories;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.generators.*;

import java.util.HashMap;
import java.util.Map;

public final class FeedEventFactory {

    private FeedEventFactory() {
    }

    private static final Map<String, EventGenerator> GENERATOR_MAP = new HashMap<String, EventGenerator>() {
        {
            put("statusmessage", new StatusMessageEventGenerator());
            put("becamefriends", new FriendshipEventGenerator());
            put("createdforumthread", new CreatedForumThreadEventGenerator());
            put("wroteforumpost", new ForumPostEventGenerator());
            put("receivedwallpost", new WallpostEventGenerator());
            put("addedfavserver", new FavoriteServerEventGenerator());
            put("levelcomplete", new CompletedLevelEventGenerator());
            put("gamereporthighlight", new GameReportEventGenerator());
            put("commentedgamereport", new CommentedGameReportEventGenerator());
            put("commentedblog", new CommentedBlogEventGenerator());
            put("gameaccess", new GameAccessEventGenerator());
            put("battlepack", new BattlePackEventGenerator());
            put("sharedgameevent", new SharedGameEventGenerator());
            put("rankedup", new RankedUpEventGenerator());
        }
    };

    public static BaseEvent create(final String event, final JsonObject jsonObject) {
        return getGenerator(event).generate(new Gson(), jsonObject);
    }

    public static BaseEvent create(final Gson gson, final String event, final JsonObject jsonObject) {
        return getGenerator(event).generate(gson, jsonObject);
    }

    private static EventGenerator getGenerator(final String event) {
        return GENERATOR_MAP.containsKey(event) ? GENERATOR_MAP.get(event) : new UnknownEventGenerator();
    }
}
