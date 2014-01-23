package com.ninetwozero.bf4intel.json.stats.reports;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BattleReportStatisticsTest {

    private static final int REPORTS_COUNT = 10;
    private static final int FAVOURITE_REPORTS_COUNT = 1;

    @Test
    public void should_successfully_parse_json() throws IOException {
        BattleReportStatistics rs = IntelJsonParser.parse("/json/report-stats.json", BattleReportStatistics.class);
        assertEquals(REPORTS_COUNT, rs.getStatsGameReports().size());
        assertEquals(FAVOURITE_REPORTS_COUNT, rs.getFavoriteReports().size());
    }
}
