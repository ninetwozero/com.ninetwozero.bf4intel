package com.ninetwozero.bf4intel.json.assignments;

import com.ninetwozero.bf4intel.util.IntelJsonParser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class AssignmentsTest {
    // 27 + 21 + 18 + 6 = 72
    private static final int EXPECTED_COUNT = 72;

    @Test
    public void should_parse_Json() throws IOException {
        Assignments assignments = IntelJsonParser.parse("/json/assignments.json", Assignments.class);
        assertEquals(EXPECTED_COUNT, assignments.getAssignments().size());
    }
}
