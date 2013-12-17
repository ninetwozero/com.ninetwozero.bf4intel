package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VehicleStatisticsTest {

    private static final int VEHICLE_GROUPS_COUNT = 8;
    private static final int VEHICLES_COUNT = 53;

    @Test
    public void should_parse_vehicles_statistics_json() throws IOException {
        VehicleStatistics stats = IntelJsonParser.parse("/json/vehicles-stats.json", VehicleStatistics.class);
        assertEquals(VEHICLE_GROUPS_COUNT, stats.getUnlockProgressions().size());
        assertEquals(VEHICLES_COUNT, stats.getVehiclesList().size());
    }
}
