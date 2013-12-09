package com.ninetwozero.bf4intel.factories;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.generators.BattlePackEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.CommentedBlogEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.CommentedGameReportEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.CompletedLevelEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.CreatedForumThreadEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.FavoriteServerEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.ForumPostEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.FriendshipEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.GameAccessEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.GameReportEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.SharedGameEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.StatusMessageEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.UnknownEventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.generators.WallpostEventGenerator;

import java.util.HashMap;
import java.util.Map;

public class FeedEventFactory {
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