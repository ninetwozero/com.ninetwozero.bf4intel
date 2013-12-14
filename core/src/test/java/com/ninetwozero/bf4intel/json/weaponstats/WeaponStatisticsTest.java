package com.ninetwozero.bf4intel.json.weaponstats;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class WeaponStatisticsTest {

    private static final int WEAPON_COUNT = 136;

    @Test
    public void should_parse_json() throws IOException {
        WeaponStatistics stats = IntelJsonParser.parse("/json/weapon-stats.json", WeaponStatistics.class);
        assertEquals(WEAPON_COUNT, stats.getWeaponsList().size());
        for (Weapon w : stats.getWeaponsList()) {
            System.out.println("put(\"" + w.getUniqueName() + "\", R.drawable.w_ " + w.getName() + ");");
        }
    }
}
