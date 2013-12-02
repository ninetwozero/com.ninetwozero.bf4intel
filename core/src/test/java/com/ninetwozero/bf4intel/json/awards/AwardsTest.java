package com.ninetwozero.bf4intel.json.awards;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import java.io.IOException;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AwardsTest {
    private static final int AWARDS_COUNT = 47;

    @Test
    public void should_parse_Json() throws IOException {
        Awards awards = IntelJsonParser.parse("/json/awards.json", Awards.class);
        assertEquals(AWARDS_COUNT, awards.getMedals().size());
        assertEquals(AWARDS_COUNT, awards.getRibbons().size());
    }
}
