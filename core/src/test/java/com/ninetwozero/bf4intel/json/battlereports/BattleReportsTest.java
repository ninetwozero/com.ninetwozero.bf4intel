package com.ninetwozero.bf4intel.json.battlereports;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class BattleReportsTest {

    @Test
    public void should_parse_Json() throws IOException {
        final BattleReportOverview report = IntelJsonParser.parse(
            "/json/battlereports.json",
            BattleReportOverview.class
        );

        assertEquals("2832658801548551060", report.getUserId());
        assertEquals("177958806", report.getPersonaId());
        assertEquals(2, report.getPlatform());
        assertEquals(7, report.getReports().size());
    }
}
