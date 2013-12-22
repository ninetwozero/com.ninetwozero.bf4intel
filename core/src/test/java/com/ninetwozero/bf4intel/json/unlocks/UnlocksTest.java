package com.ninetwozero.bf4intel.json.unlocks;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class UnlocksTest {
    @Test
    public void should_parse_Json() throws IOException {
        final VehicleUnlocks unlocks = IntelJsonParser.parse(
            "/json/vehicle-unlocks.json",
            VehicleUnlocks.class
        );
        assertEquals(8, unlocks.getUnlockMap().size());
    }
}
