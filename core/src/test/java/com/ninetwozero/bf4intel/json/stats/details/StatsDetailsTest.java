package com.ninetwozero.bf4intel.json.stats.details;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class StatsDetailsTest {

    @Test
    public void should_successfully_parse_json() throws IOException {
        StatsDetails details = IntelJsonParser.parse("/json/details-stats.json", StatsDetails.class);
        assertNotNull(details.getGeneralStats().getAccuracy());
        assertNotNull(details.getGeneralStats().getAssaultScore());
        assertNotNull(details.getGeneralStats().getAvengerKills());
    }
}
