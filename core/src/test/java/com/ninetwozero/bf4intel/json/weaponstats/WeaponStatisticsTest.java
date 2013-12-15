package com.ninetwozero.bf4intel.json.weaponstats;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class WeaponStatisticsTest {

    private static final int WEAPON_COUNT = 136;

    @Test
    public void should_parse_json() throws IOException {
        WeaponStatistics stats = IntelJsonParser.parse("/json/weapon-stats.json", WeaponStatistics.class);
        assertEquals(WEAPON_COUNT, stats.getWeaponsList().size());
    }
}
