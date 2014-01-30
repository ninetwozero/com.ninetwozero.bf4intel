package com.ninetwozero.bf4intel.json.news;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ninetwozero.bf4intel.json.battlereports.BattleReportOverview;
import com.ninetwozero.bf4intel.util.IntelJsonParser;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class NewsTest {

    @Test
    public void should_parse_Json_for_listing() throws IOException {
        final NewsListRequest request = fetchJsonForListing("/json/news_listing.json");
        assertNotNull(request.getArticles());
        assertTrue(request.getArticles().size() > 0);
        assertTrue(request.getPageCount() > 0);
        assertTrue(request.getPageId() != 0);
    }

    @Test
    public void should_parse_Json_for_single_article() throws IOException {
        final NewsArticleRequest request = fetchJsonForSingleArticle("/json/news_single_article.json");
        assertNotNull(request.getArticle());
        assertNotNull(request.getArticle().getTitle());
        assertNotNull(request.getArticle().getId());
        assertNotNull(request.getArticle().getContent());
    }

    private NewsListRequest fetchJsonForListing(final String filename) throws IOException {
        Reader reader = new InputStreamReader(NewsListRequest.class.getResourceAsStream(filename));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject dataJson = parser.parse(reader).getAsJsonObject().getAsJsonObject("context");
        reader.close();

        return gson.fromJson(dataJson, NewsListRequest.class);
    }

    private NewsArticleRequest fetchJsonForSingleArticle(final String filename) throws IOException {
        Reader reader = new InputStreamReader(NewsArticleRequest.class.getResourceAsStream(filename));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject dataJson = parser.parse(reader).getAsJsonObject().getAsJsonObject("context");
        reader.close();

        return gson.fromJson(dataJson, NewsArticleRequest.class);
    }
}
