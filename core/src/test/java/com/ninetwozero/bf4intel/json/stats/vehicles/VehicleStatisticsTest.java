package com.ninetwozero.bf4intel.json.stats.vehicles;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VehicleStatisticsTest {

    private static final int VEHICLE_UNLOCK_PROGRESSION_COUNT = 8;
    private static final int VEHICLES_COUNT = 53;
    private static final int VEHICLE_GROUPS_COUNT = 15;

    @Test
    public void should_parse_vehicles_statistics_json() throws IOException {
        VehicleStatistics stats = IntelJsonParser.parse("/json/vehicles-stats.json", VehicleStatistics.class);
        assertEquals(VEHICLE_UNLOCK_PROGRESSION_COUNT, stats.getUnlockProgressions().size());
        assertEquals(VEHICLES_COUNT, stats.getVehiclesList().size());
    }

    @Test
    public void should_succeed_with_vehicle_grouping() throws IOException {
        VehicleStatistics stats = IntelJsonParser.parse("/json/vehicles-stats.json", VehicleStatistics.class);
        List<GroupedVehicleStats> groupedVehicles = stats.fetchGroupVehicles();
        assertEquals(VEHICLE_GROUPS_COUNT, groupedVehicles.size());
    }
}
