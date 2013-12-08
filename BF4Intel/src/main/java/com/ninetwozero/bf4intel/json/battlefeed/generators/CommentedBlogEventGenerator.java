package com.ninetwozero.bf4intel.json.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.json.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedBlogEvent;

public class CommentedBlogEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new CommentedBlogEvent(
            EventType.COMMENTED_BLOG,
            jsonObject.get("blogTitle").getAsString(),
            jsonObject.get("blogCommentBody").getAsString()
        );
    }
}
