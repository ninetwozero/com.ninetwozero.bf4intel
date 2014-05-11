package com.ninetwozero.bf4intel.factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninetwozero.bf4intel.json.assignments.AssignmentCriteria;
import com.ninetwozero.bf4intel.json.assignments.AssignmentCriteriaDeserializer;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequisite;
import com.ninetwozero.bf4intel.json.assignments.AssignmentPrerequisiteDeserializer;
import com.ninetwozero.bf4intel.json.assignments.AssignmentReward;
import com.ninetwozero.bf4intel.json.assignments.AssignmentRewardDeserializer;
import com.ninetwozero.bf4intel.json.battlefeed.FeedItem;
import com.ninetwozero.bf4intel.utils.FeedItemDeserializer;

public class GsonProvider {
    private static Gson instance;

    private GsonProvider() {
    }

    public static Gson getInstance() {
        if (instance == null) {
            instance = createNewInstance();
        }
        return instance;
    }

    private static Gson createNewInstance() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(FeedItem.class, new FeedItemDeserializer());
        builder.registerTypeAdapter(AssignmentPrerequisite.class, new AssignmentPrerequisiteDeserializer());
        builder.registerTypeAdapter(AssignmentReward.class, new AssignmentRewardDeserializer());
        builder.registerTypeAdapter(AssignmentCriteria.class, new AssignmentCriteriaDeserializer());
        return builder.create();
    }
}
