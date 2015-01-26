package com.ninetwozero.bf4intel.json.battlepacks;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class BattlepacksTest {

    @Test
    public void should_parse_Json() throws IOException {
        final Battlepacks battlepacks = IntelJsonParser.parse(
                "/json/battlepacks.json",
                Battlepacks.class
        );

        assertEquals(3, battlepacks.getWeaponPackList().size());
    }
}
