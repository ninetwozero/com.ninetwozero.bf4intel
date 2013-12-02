package com.ninetwozero.bf4intel.factories;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.CommentedBlogEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.CommentedGameReportEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.CompletedLevelEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.CreatedForumThreadEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.FavoriteServerEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.ForumPostEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.FriendshipEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.GameAccessEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.GameReportEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.StatusMessageEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.UnknownEventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators.WallpostEventGenerator;

import java.util.HashMap;
import java.util.Map;

public class FeedEventFactory {
    private static final Map<String, EventGenerator> GENERATOR_MAP = new HashMap<String, EventGenerator>() {{
        put("statusmessage", new StatusMessageEventGenerator());
        put("becamefriends", new FriendshipEventGenerator());
        put("createdforumthread", new CreatedForumThreadEventGenerator());
        put("wroteforumpost", new ForumPostEventGenerator());
        put("receivedwallpost", new WallpostEventGenerator());
        put("addedfavserver", new FavoriteServerEventGenerator());
        put("levelcomplete", new CompletedLevelEventGenerator());
        put("gamereporthighlight", new GameReportEventGenerator());
        put("commentedgamereport", new CommentedGameReportEvent());
        put("commentedblog", new CommentedBlogEventGenerator());
        put("gameaccess", new GameAccessEventGenerator());
        //put("sharedgameevent", EventType.SHAREDGAMEEVENT); // TODO: Don't even get me started on this shit
    }};

    public static BaseEvent create(final String event, final JsonObject jsonObject) {
        return getGenerator(event).generate(new Gson(), jsonObject);
    }

    public static BaseEvent create(final Gson gson, final String event, final JsonObject jsonObject) {
        return getGenerator(event).generate(gson, jsonObject);
    }

    private static EventGenerator getGenerator(final String event) {
        return GENERATOR_MAP.containsKey(event)? GENERATOR_MAP.get(event) : new UnknownEventGenerator();
    }
}
