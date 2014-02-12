package com.ninetwozero.bf4intel.json.battlefeed;

import com.google.gson.Gson;
import com.ninetwozero.bf4intel.factories.GsonProvider;
import com.ninetwozero.bf4intel.json.battlefeed.events.BattlePackEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.CommentedBlogEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.CompletedLevelEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.CreatedForumThreadEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.FavoriteServerEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.ForumPostEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.FriendshipEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameAccessEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.GameReportEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.RankedUpEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.SharedGameEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.StatusMessageEvent;
import com.ninetwozero.bf4intel.json.battlefeed.events.WallpostEvent;
import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BattleFeedTest {
    private static BattleFeed battleFeed;
    private static final List<EventType> VALID_TYPES = new ArrayList<EventType>() {
        {
            add(EventType.LEVEL_COMPLETE);
            add(EventType.ADDED_FAV_SERVER);
            add(EventType.SHARED_GAME_EVENT);
            add(EventType.SHARED_GAME_EVENT);
            add(EventType.GAME_REPORT);
            add(EventType.BECAME_FRIENDS);
            add(EventType.GAME_ACCESS);
            add(EventType.RECEIVED_WALL_POST);
            add(EventType.COMMENTED_GAME_REPORT);
            add(EventType.RANKED_UP);
            add(EventType.STATUS_MESSAGE);
            add(EventType.BATTLE_PACK);
            add(EventType.SHARED_GAME_EVENT);
            add(EventType.WROTE_FORUM_POST);
            add(EventType.CREATED_FORUM_THREAD);
            add(EventType.COMMENTED_BLOG);
        }
    };

    @BeforeClass
    public static void setup() throws IOException {
        final Gson gson = GsonProvider.getInstance();
        battleFeed = IntelJsonParser.parse(
            "/json/battlefeed.json",
            BattleFeed.class,
            gson
        );
    }

    @Test
    public void shouldFindTheCorrectFeedItemTypesinList() {
        for (int i = 0, max = battleFeed.getFeedItems().size(); i < max; i++) {
            assertEquals(
                VALID_TYPES.get(i),
                battleFeed.getFeedItems().get(i).getEvent().getEventType()
            );
        }
    }

    @Test
    public void shouldParseLevelCompleteEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(0);
        final CompletedLevelEvent event = (CompletedLevelEvent) feedItem.getEvent();

        assertEquals("SP_Prison", event.getLevelName());
        assertEquals("NORMAL", event.getDifficulty());
        assertEquals(2, event.getGameType());
        assertEquals(null, event.getPartner());
    }

    @Test
    public void shouldParseAddedFavoriteServerEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(1);
        final FavoriteServerEvent event = (FavoriteServerEvent) feedItem.getEvent();

        assertEquals("d3f855e9-b9d4-4eda-8339-e387454260c3", event.getServerGuid());
        assertEquals("=ADK= #1 | 24/7 Domination  | ADKGamers.com |", event.getServerName());
    }

    @Test
    public void shouldParseSharedGameEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(2);
        final SharedGameEvent event = (SharedGameEvent) feedItem.getEvent();

        assertEquals("404735265846516672", event.getGameId());
        assertEquals("statItems", event.getCategory().getArrayKey());
        assertEquals(2, event.getItems().length);
    }

    @Test
    public void shouldParseGameReportEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(4);
        final GameReportEvent event = (GameReportEvent) feedItem.getEvent();

        assertEquals("-[DICE]- BF4 CONQUEST - Normal 8318", event.getServerName());
        assertEquals("MP_Damage", event.getMap());
        assertEquals(1569, event.getDuration());
        assertEquals(1, event.getGameMode());
    }

    @Test
    public void shouldParseFriendshipEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(5);
        final FriendshipEvent event = (FriendshipEvent) feedItem.getEvent();

        assertEquals("2832659598363461442", event.getFriendId());
        assertEquals("kyleglen", event.getFriend().getUsername());
    }

    @Test
    public void shouldParseGameAccessEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(6);
        final GameAccessEvent event = (GameAccessEvent) feedItem.getEvent();

        assertEquals(2048, event.getGame());
        assertEquals(524288, event.getExpansion());
        assertEquals(0, event.getPlatform());
    }

    @Test
    public void shouldParseWallpostEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(7);
        final WallpostEvent event = (WallpostEvent) feedItem.getEvent();

        assertEquals("After this week, I'll be working days and i can play with you guys again.", event.getMessage());
        assertEquals("2955061645803385831", event.getSender().getId());
        assertEquals("NerdNugget", event.getSender().getUsername());
    }

    @Test
    public void shouldParseRankedUpEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(9);
        final RankedUpEvent event = (RankedUpEvent) feedItem.getEvent();

        assertEquals("410582818208921344", event.getGameHistoryId());
        assertEquals("WARSAW_ID_P_RANK30_NAME", event.getName());
        assertEquals(30, event.getRank());
    }

    @Test
    public void shouldParseStatusMessageEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(10);
        final StatusMessageEvent event = (StatusMessageEvent) feedItem.getEvent();

        assertEquals("New assignment been added PHANTOM PROSPECT. COOL!!! Assignment requirements missing, FAIL!!!", event.getMessage());
        assertEquals("null", event.getPreview()); //Ugh, String "null" in JSON
    }

    @Test
    public void shouldParseBattlePackEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(11);
        final BattlePackEvent event = (BattlePackEvent) feedItem.getEvent();

        assertEquals("WARSAW_ID_P_BATTLEPACK_WEAPON_AK_5C", event.getName());
        assertEquals("weapon_ak5c_1", event.getPackKey());
        assertEquals(4, event.getItems().size());
    }

    @Test
    public void shouldParseForumPostEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(13);
        final ForumPostEvent event = (ForumPostEvent) feedItem.getEvent();

        assertEquals("tomorrow is monday", event.getThreadTitle());
        assertEquals("Technically speaking, tomorrow is Tuesday (here in Sweden). :-", event.getPostBody());
    }

    @Test
    public void shouldParseCreatedForumThreadEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(14);
        final CreatedForumThreadEvent event = (CreatedForumThreadEvent) feedItem.getEvent();

        assertEquals("Neat Black Friday deals?", event.getTitle());
        assertEquals("Hello world!", event.getContent());
    }

    @Test
    public void shouldParseCommentedBlogEventCorrectly() {
        final FeedItem feedItem = battleFeed.getFeedItems().get(15);
        final CommentedBlogEvent event = (CommentedBlogEvent) feedItem.getEvent();

        assertEquals("Full Week of Double XP Live", event.getBlogTitle());
        assertEquals("Hello world!", event.getComment());
    }
}
